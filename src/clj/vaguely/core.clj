(ns vaguely.core
  (:require [vaguely.server :as srv]
            [taoensso.timbre :as log]
            [environ.core :as env]
            [org.parkerici.multitool.core :as u]
            [org.parkerici.multitool.cljcore :as ju]
            )
  (:gen-class))

(defn -main
  [& [port]]
  (future
    (try
      (let [port (or port (:port env/env))]
        (log/info (format "starting web server on port %s" port))
        (srv/start (u/coerce-numeric port))
        (log/info "web server started on " port)
        (ju/open-url (format "http://localhost:%s" port)))
      (catch Exception e
        (log/error e)))))
