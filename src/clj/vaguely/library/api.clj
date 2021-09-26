(ns vaguely.library.api
  (:require [vaguely.library.dynamo :as dynamo])
  )

;;; Versioned because I want to be able to evolve the block ontology
;;; Although keeping multiple versions running would be a pain. 


(def table-name "vaguely_library")
(def version 1)                         


;;; Dynamo uses a paging scheme different than other AWS services.
;;; See https://stackoverflow.com/questions/66752484/dynamodb-query-returns-incomplete-data
;;; TODO this is not scalable
(defn list-items-1
  [start-key]
  (let [resp (dynamo/invoke-with-error
              :dynamodb
              {:op :Scan
               :request {:TableName table-name
                         :ExclusiveStartKey start-key
                         }})]
    (concat 
     (->> resp
          :Items
          (map dynamo/untag-attributes))
     (when-let [key (:LastEvaluatedKey resp)]
       (list-items-1 key)
       ))))

(defn list-items
  []
  (->> (list-items-1 nil)
       (sort-by :date-created)
       reverse))

(defn assign-uuid
  [map]
  (assoc map :uuid (str (java.util.UUID/randomUUID))))

(defn add-version
  [map]
  (assoc map :version version))

(def last-item (atom nil))

(defn write-item
  [map]
  (reset! last-item map)
  (dynamo/invoke-with-error
   :dynamodb
   {:op :PutItem
    :request {:TableName table-name
              :Item (-> map
                        add-version
                        assign-uuid
                        dynamo/tag-attributes)}})
  {:status 200
   :body "{\"message\": \"Saved\"}"     ;this json shouldn't be necessary, but the middleware is failing me
   :header {}})

(defn read-item
  [uuid]
  (-> (dynamo/invoke-with-error
       :dynamodb
       {:op :GetItem
        :request {:TableName table-name
                  :Key {:uuid {:S uuid}}}})
      :Item
      dynamo/untag-attributes
      ))

