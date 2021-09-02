(ns vaguely.server
  (:require [vaguely.data :as data]
            [vaguely.library.api :as library]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer [context routes GET POST]]
            [compojure.route :as route]
            [taoensso.timbre :as log]
            [ring.util.response :as response]
            [ring.middleware.defaults :as middleware]
            [ring.middleware.gzip :refer [wrap-gzip]]
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
  [url format]
  (log/infof "Data request %s" url)
  (let [data (data/read-file-maps url (keyword format))]
    {:status 200
     :headers {}
     :body data}  
  ))

(defn app-routes
  []
  (routes
   (GET "/" [] (response/redirect "index.html"))
   (GET "/health" [] (response/response "ok"))
   (context "/api" []
            (GET "/data" [url format]
                 (response/response (handle-data url format)))
            (context "/library" []
                     (GET "/list" [] (library/list-items))
                     (GET "/get" [id] (library/read-item id))
                     (POST "/save" [item] (library/write-item item))))
   (route/not-found "Not Found")))

(def site-defaults
  (-> middleware/site-defaults                   ;Note: should separate out site and API
      (assoc-in [:security :anti-forgery] false)          ;interfering with save?
      )) 

(defn app
  []
  (routes
   (-> (app-routes)
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
  ([port] (start port (app)))
  ([port handler]
   (stop)
   (reset! server (jetty/run-jetty handler {:port port :join? false}))))



