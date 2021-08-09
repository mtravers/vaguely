(defproject vaguely "0.0.1-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.parkerici/multitool "0.0.15"]

                 ;; Front end

                 [reagent "0.8.1"]
                 [re-frame "0.10.9"]
                 [org.parkerici/blockoid "0.3.6"]
                 [metasoarous/oz "1.6.0-alpha6"] ;warning: later versions seem to have broken dependencies
                 [cljs-ajax "0.8.0"]

                 ;; Back end

                 ;; Ring and friends
                 [org.eclipse.jetty/jetty-client "9.4.12.v20180830"] ;has to match ring version of jetty
                 [org.eclipse.jetty/jetty-server "9.4.12.v20180830"]
                 [org.eclipse.jetty/jetty-http "9.4.12.v20180830"]
                 [org.eclipse.jetty/jetty-util "9.4.12.v20180830"]
                 [ring "1.8.0"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.1" :exclusions [ring.core ring.codec]]
                 [ring-middleware-format "0.7.4" :exclusions [javax.xml.bind/jaxb-api]]
                 [bk/ring-gzip "0.3.0"]

                 ;; Other

                 [clj-http "3.12.1"]
                 [compojure "1.6.1" :exclusions [ring.core ring.codec]]
                 [me.raynes/fs "1.4.6"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/data.csv "0.1.4"]                  

                 ]

  :repositories [["local" {:url ~(str (.toURI (java.io.File. "mvn")))
                           :checksum :ignore}]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-shell "0.5.0"]
            [lein-localrepo "0.5.4"]    ;for multitool, until its public
            ]

  :min-lein-version "2.5.3"

  :source-paths ["src/cljc" "src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:server-port 3456
             :css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "1.0.0"]
                   [figwheel-sidecar "0.5.19"
                    :exclusions [org.clojure/clojurescript]]
                   [day8.re-frame/re-frame-10x "0.4.2"
                    :exclusions [cljsjs/create-react-class
                                 org.clojure/clojurescript]]
                   ]
    :plugins      [[lein-figwheel "0.5.19"]]
;    :source-paths ["dev"]
    }

   :prod { }}

  :prep-tasks []

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "src/cljc"]
     :figwheel     {:on-jsload           "vaguely.core/figwheel-hook"   } ; "vaguely.core/init"
     :compiler     {:main                 vaguely.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                           day8.re-frame-10x.preload]
                    :optimizations        :none
                    :infer-externs        true
                    :external-config      {:devtools/config {:features-to-install :all}}
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true
                                           goog.DEBUG true}}}
    
    {:id           "prod"
     :source-paths ["src/cljs" "src/cljc"]
     :compiler     {:main            vaguely.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir      "resources/public/js/compiled/outprod"
                    ;; TODO :advanced is better but requires special handling for Blockly refs
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]}

  )
