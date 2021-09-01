(ns vaguely.data
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.string :as str]
            [me.raynes.fs :as fs]
            [org.parkerici.multitool.core :as u]
            ))

(defn read-csv-file [fname & [separator]]
  (with-open [reader (-> fname
                         io/input-stream
;;;                         BOMInputStream. ;Removes garbage character
                         io/reader)]
    (doall
     (csv/read-csv reader :separator separator))))

;;; TODO strip " %" 
(defn csv-coerce-value [v]
  (if (empty? v)
    nil
    (u/coerce-numeric v)))

(defn csv-coerce-values
  [csv-data]
  (cons (first csv-data)
        (map (fn [row] (map csv-coerce-value row)) (rest csv-data))))

(defn csv-coerce-head
  [head]
  (if (empty? head)
    :__id                               
    ;; Vega definitely doesn't like ., not sure about other chars
    (keyword (str/replace head #"[\.]" "_"))))   ;  ,\(\):

(defn csv-data->maps
  [csv-data]
  (map zipmap
       (->> (map csv-coerce-head (first csv-data)) ;; First row is the header
            repeat)
       (rest csv-data)))

(defn read-csv-file-maps
  "Assumes a header line"
  [fname & [separator]]
  (->> (read-csv-file fname separator)
       csv-coerce-values              ;if -s flag is set, we co coerce and then uncoerce, which is inefficient but works
       csv-data->maps))

(defn read-json-file
  [fname]
  (with-open [s (io/reader fname)]
    (json/read s :key-fn csv-coerce-head)))

;;; Fname is really a URL and maybe should be parsed with a proper URL library
(defn infer-format
  [fname]
  (let [filetype (fs/extension fname)]
    (and filetype
         (keyword (subs filetype 1)))))

(defn read-file-maps
  [fname format]
  (let [format (if (or (nil? format)
                       (= format :inferred))
                 (infer-format fname)
                 format)]
    (case format
      :csv (read-csv-file-maps fname \,)
      :tsv (read-csv-file-maps fname \tab,)
      :json (read-json-file fname)
      (infer-format fname))))              ;or could error

        
