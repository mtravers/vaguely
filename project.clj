(defproject vaguely "0.0.1-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async "0.6.532"]
                 [org.parkerici/multitool "0.0.15"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.9"]
                 [org.parkerici/blockoid "0.3.6"]
                 [testdouble/clojurescript.csv "0.5.1"] 
                 [metasoarous/oz "1.6.0-alpha6"] ;warning: later versions seem to have broken dependencies
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
