(ns vaguely.library.dynamo
  (:require [cognitect.aws.credentials :as credentials]
            [org.parkerici.multitool.core :as u]
            [cognitect.aws.client.api :as aws]
            ))

;;; Fairly generic stuff for dynamodb



(u/defn-memoized client [service]
  (aws/client {:api service
               :region "us-west-2"
               :credentials-provider (credentials/environment-credentials-provider)}))

(defn invoke-with-error
  [service op]
  (let [result (aws/invoke (client service) op)]
    (when (or (:Error result)
              (:cognitect.anomalies/category result))
      (throw (ex-info "AWS error" {:op op :aws-result result})))
    result))


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

(defn untag-attributes
  [map]
  (u/map-values (comp u/coerce-numeric first vals) map))

