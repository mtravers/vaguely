(ns vaguely.core
  (:require [vaguely.server :as srv]
            [taoensso.timbre :as log]
            [environ.core :as env]
            [org.parkerici.multitool.core :as u]
            )
  (:gen-class))

(defn -main
  []
  (future
    (try
      (let [{:keys [port]} env/env]
        (log/info (format "starting web server on port %s" port))
        (srv/start (u/coerce-numeric port)))
      (log/info "web server started (presumbably)")
      (catch Exception e
        (log/error e)))))
