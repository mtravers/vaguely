(ns vaguely.data
  (:require [re-frame.core :as rf]
            [vaguely.canned :as canned]
            [vaguely.api :as api]
            )
  )

(def source "https://forge.scilab.org/index.php/p/rdataset/source/file/master/csv/datasets/trees.csv")
(def covid "https://covidtracking.com/data/download/all-states-history.csv")



(def data-color "#e09f3e")            ;TODO elsewhere

(defn blockdefs
  []
  (cons
   {:type "data-url"
    :output "data"
    :colour data-color
    :message0 "url %1"
    :args0 [{:name "url"
             :type "field_input"
             :spellcheck false}]
    }
   (canned/blockdefs)))


(rf/reg-event-fx
 :set-data-url
 (fn [_ [_ url]]
   (api/ajax-get "/api/data"
                 {:url-params {:url url}
                  :handler (fn [resp]
                             (rf/dispatch [:set-data (:body resp)]))})
   {}))


(rf/reg-event-db
 :set-data
 (fn [db [_ data]]
   (assoc db
          :data data
          :display-columns (keys (first data))))) ;TODO improve


(rf/reg-sub :display-columns
            (fn [db _]
              (:display-columns db)))

(rf/reg-sub :data
            (fn [db _]
              (:data db)))

(defn toolbox
  []
  `[:category "Data" {}
    [:block "data-url" {}
     [:field "url" ~covid]]
    ~@(canned/toolbox)])
