(ns vaguely.library.dynamo
  (:require [cognitect.aws.credentials :as credentials]
            [org.parkerici.multitool.core :as u]
            [cognitect.aws.client.api :as aws]
            ))

;;; Fairly generic stuff for dynamodb

(def table-name "vaguely_library")

(u/defn-memoized client [service]
  (aws/client {:api service
               :region "us-west-2"
               :credentials-provider (credentials/environment-credentials-provider)}))

(defn invoke-with-error
  [service op]
  (let [result (aws/invoke service op)]
    (when (or (:Error result)
              (:cognitect.anomalies/category result))
      (throw (ex-info "AWS error" {:op op :aws-result result})))
    result))

(defn list-library
  []
  (invoke-with-error (client :dynamodb)
                     {:op :Scan
                      :request {:TableName table-name}}))

(defn assign-uuid
  [map]
  (assoc map :uuid (str (java.util.UUID/randomUUID))))

#_  #:shape{AttributeValue
          {:L [:seq-of shape/AttributeValue],
           :M [:map-of string shape/AttributeValue],
           :NS [:seq-of string],
           :BOOL boolean,
           :B blob,
           :SS [:seq-of string],
           :NULL boolean,
           :S string,
           :BS [:seq-of blob],
           :N string}}

;;; This could do more. 
(defn tag-attribute
  [v]
  (cond (boolean? v) {:BOOL v}
        (sequential? v) {:L (map tag-attribute v)}
        :else
        {:S (str v)}))

(defn tag-attributes
  [map]
  (u/map-values tag-attribute map))

(defn write-item
  [map]
  (invoke-with-error (client :dynamodb)
                     {:op :PutItem
                      :request {:TableName table-name
                                :Item (-> map
                                          assign-uuid
                                          tag-attributes)}}))

