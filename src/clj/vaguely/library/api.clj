(ns vaguely.library.api
  (:require [vaguely.library.dynamo :as dynamo])
  )

;;; Versioned because I want to be able to evolve the block ontology
;;; Although keeping multiple versions running would be a pain. 


(def table-name "vaguely_library")
(def version 1)                         

(defn list
  []
  (let [resp (dynamo/invoke-with-error
              :dynamodb
              {:op :Scan
               :request {:TableName table-name}})]
    (map dynamo/untag-attributes (:Items resp))))

(defn assign-uuid
  [map]
  (assoc map :uuid (str (java.util.UUID/randomUUID))))

(defn add-version
  [map]
  (assoc map :version version))

(defn write-item
  [map]
  (dynamo/invoke-with-error
   :dynamodb
   {:op :PutItem
    :request {:TableName table-name
              :Item (-> map
                        add-version
                        assign-uuid
                        dynamo/tag-attributes)}}))



(defn read-item
  [uuid]
  (dynamo/invoke-with-error
   :dynamodb
   {:op :GetItem
    :request {:TableName table-name
              :Index uuid}}))



