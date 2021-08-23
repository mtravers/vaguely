(ns vaguely.api
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax]
            ))

;;; API utils
;;; Note: transit decoding happens magically

(def standard-ajax-options
  {:error-handler #(rf/dispatch [:error %1])
   :format :transit
   :response-format :transit
   })

(defn ajax-get
  [uri options]
  (ajax/GET uri
            (merge standard-ajax-options options )))

(defn ajax-post
  [uri options]
  (ajax/POST uri
            (merge standard-ajax-options options )))





