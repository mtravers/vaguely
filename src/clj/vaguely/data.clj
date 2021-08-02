(ns vaguely.data
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [org.parkerici.multitool.core :as u]
            [org.parkerici.multitool.cljcore :as ju]
            )  )

(defn read-csv-file [fname & [separator]]
  (with-open [reader (-> fname
                         io/input-stream
;                         BOMInputStream. ;Removes garbage character
                         io/reader)]
    (doall
     (csv/read-csv reader :separator (or separator \,)))))

(defn csv-coerce-value [v]
  (if (empty? v)
    nil
    (u/coerce-numeric v)))

(defn csv-coerce-values
  [csv-data]
  (cons (first csv-data)
        (map (fn [row] (map csv-coerce-value row)) (rest csv-data))))

(defn keywordize
  [head]
  (if (empty? head)
    :__id                               
    (keyword head)))

(defn csv-data->maps
  [csv-data]
  (map zipmap
       (->> (map keywordize (first csv-data)) ;; First row is the header
            repeat)
       (rest csv-data)))

(defn read-csv-file-maps
  "Assumes a header line"
  [fname & [separator]]
  (->> (read-csv-file fname separator)
       csv-coerce-values              ;if -s flag is set, we co coerce and then uncoerce, which is inefficient but works
       csv-data->maps))


