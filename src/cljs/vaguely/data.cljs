(ns vaguely.data
  (:require [re-frame.core :as rf]
            )
  )


(def source "https://forge.scilab.org/index.php/p/rdataset/source/file/master/csv/datasets/trees.csv")
;;; XHTTP – but no, that won't work

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
