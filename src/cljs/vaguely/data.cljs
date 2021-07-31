(ns vaguely.data
  (:require [re-frame.core :as rf]
            )
  )


(def source "https://forge.scilab.org/index.php/p/rdataset/source/file/master/csv/datasets/trees.csv")
;;; XHTTP – but no, that won't work


(rf/reg-sub :display-columns
            (fn [db _]
              (keys (first (:data db))))) ;TODO improve

(rf/reg-sub :data
            (fn [db _]
              (:data db)))
