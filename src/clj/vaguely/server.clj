(ns org.parkerici.vaguely.server
  (:require [vaguely.data :as data]
            [org.parkerici.multitool.core :as u]
            [taoensso.timbre :as log]
            [environ.core :as env]
;            [ring.logger :as logger]
            [clj-http.client :as client]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer [context routes GET POST]]
            [compojure.route :as route]
            [ring.util.response :as response]
            [ring.middleware.defaults :as middleware]
            [clojure.data.json :as json]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.format :refer [wrap-restful-format]]))


(defn wrap-exception-handling
  [handler]
  (fn [request]
    (try
      (handler request)
      (catch clojure.lang.ExceptionInfo e
        {:status 500 :headers {} :body {:error (ex-message e) :data (ex-data e)}})
      (catch Throwable e
        {:status 500 :headers {} :body {:error (print-str e)}}))))

(defn handle-data
  [url]
  (let [data (data/read-csv-file-maps url)]
    {:status 200
     :headers {}
   ;; Warning: this breaks the file-upload response because it isn't under wrapper
     :body data}  
  ))

(defn app-routes
  [config]
  (routes
   (GET "/" [] (response/redirect "index.html"))
   (GET "/health" [] (response/response "ok"))
   #_ ;; Someday
   (GET "/library" [] (library-view/view))
   (context "/api" []
     (GET "/data" [url]
          (response/response (handle-data url))))
   (route/not-found "Not Found")))

(def site-defaults
  (-> middleware/site-defaults
      (assoc-in [:security :anti-forgery] false)          ;interfering with save?
      #_ (assoc-in [:static :resources] nil)))                 ;this needs to go after oauth

(defn app
  [config]
  (routes
   (-> (app-routes config)
       #_
       (logger/wrap-with-logger          ;hook Ring logger to Timbre
        {:request-keys [:request-method :uri :remote-addr]
         :transform-fn identity
         :log-fn (fn [{:keys [level throwable message]}]
                   (log/log level throwable message))})
       #_
       (wrap-resource "public" {:allow-symlinks? true})
       (middleware/wrap-defaults site-defaults)
       wrap-exception-handling
       wrap-restful-format
       wrap-gzip
       )))

;;; Server

(defonce server (atom nil))

(defn stop
  []
  (when @server
    (.stop @server)))

(defn start
  ([config port] (start config port (app config)))
  ([config port handler]
   (stop)
   (reset! server (jetty/run-jetty handler {:port port :join? false}))))



