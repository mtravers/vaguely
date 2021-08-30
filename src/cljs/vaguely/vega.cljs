(ns vaguely.vega
  (:require [vaguely.data :as data] 
            [org.parkerici.multitool.core :as u]
            [re-frame.core :as rf]
            [clojure.walk :as walk]
            [oz.core :as oz]
            ))

;;; Generates the Vega spec from blocks and data.
;;; Note: see blockdefs.cljc for 

(defmulti vega-spec (fn [block] (:type block)))

;;; Pseudo block (not used, but a way to support multiple layers/marks)
(defmethod vega-spec "graph" [block]
  (let [layers (map vega-spec (:layers block))]
    (case (count layers)
      0 nil
      1 (first layers)
      {:layer layers})))

(def default-height 500)
(def default-width 500)

(defmethod vega-spec "layer" [block]
  (when-let [data-block (get-in block [:children "data"])]
    (let [data (data/block-data data-block)]
      {:mark {:type (get-in block [:children "mark"])
              :tooltip {:content "data"}}
       :encoding (vega-spec (get-in block [:children "encoding"]))
       ;; The default default is too small...this is not always right but better than nothing
       :height default-height
       :width default-width
       :data {:values data}
       })))

(defmethod vega-spec "encoding" [block]
  (assoc (vega-spec (get-in block [:children :next]))
         (get-in block [:children "attribute"])
         {:field (get-in block [:children "field"])
          :type (get-in block [:children "type"])
          :sort "ascending"
          }))

(defmethod vega-spec "count_encoding" [block]
  (assoc (vega-spec (get-in block [:children :next]))
         (get-in block [:children "attribute"])
         {:aggregate "count"}))

(defmethod vega-spec "aggregate_encoding" [block]
  (assoc (vega-spec (get-in block [:children :next]))
         (get-in block [:children "attribute"])
         {:field (get-in block [:children "field"])
          :aggregate (get-in block [:children "aggregate"])}))

(defmethod vega-spec :default [block]
  (throw (ex-info (str "Don't know how to generate Vega for " (:type block))
                  {:block block})))


;;; V2 blocks



(defmethod vega-spec "encoding2" [block]
  (assoc (vega-spec (get-in block [:children :next]))
         (get-in block [:children "attribute"])
         (vega-spec (get-in block [:children "encoding_attribute"]))))

(defmethod vega-spec "encoding_field" [block]
  (merge
   {:field (get-in block [:children "field"])    }
   (vega-spec (get-in block [:children :next]))))

;;; Layers

;;; Transform a field to a repeat (note: this gets further transformed after query is built, see repeat-transform
(defn- repeat-field
  [existing new]
  (if (map? existing)
    (update existing :repeat conj new)
    {:repeat [existing new]}))

(defmethod vega-spec "encoding_field" [block]
  (let [base (vega-spec (get-in block [:children :next]))
        new-field (get-in block [:children "field"])]
    (if-let [existing-field (get base :field)]
      (assoc base :field (repeat-field existing-field new-field))
      (assoc base :field new-field))))

(defn- repeat-transform
  [vspec]
  (let [repeat (atom nil)
        munged 
        (walk/prewalk
         (fn [x]
           (if-let [r (and (map? x) (:repeat x))]
             (do
               (when @repeat
                 ;; TODO make this user-visible, this is something a user could easily build and need to tell them why...
                 (throw (ex-info "Multiple repeats detected" {:fields [@repeat r]})))
               (reset! repeat r)
               (assoc x :repeat "layer"))
             x))
         vspec)]
    (if @repeat
      ;; TODO pull :data to outer layer? Apparently not necessary
      {:repeat {:layer @repeat}
       :spec (update-in munged
                        [:encoding "color"]
                        (fn [x]
                          (or x
                              {:datum {:repeat "layer"}
                               :type "nominal"})))}
      vspec)))

(defmethod vega-spec "encoding_scale" [block]
  (merge
   {:scale {:type (get-in block [:children "scale"]) }}
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_type" [block]
  (merge
   {:type (get-in block [:children "type"]) }
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_value" [block]
  (merge
   {:value (u/coerce-numeric (get-in block [:children "value"])) }
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_aggregate" [block]
  (merge
   {:aggregate (get-in block [:children "aggregate"])}
   (vega-spec (get-in block [:children :next]))))

;;; Terminates recursion down :next chain
(defmethod vega-spec nil [_block]
  {})

(defn generate-vega-spec
  []
  (let [; data @(rf/subscribe [:data])    ;TOOD get data from block
        blocks @(rf/subscribe [:compact-all])
        vega-block (u/something #(= "layer" (:type %)) blocks)]
    (-> vega-block
        vega-spec
        ;; Maybe other post vega-spec transforms
        repeat-transform)))

(defn render
  "React component showing the graph"
  []
  (let [spec (generate-vega-spec)]
    (if (empty? spec)
      [:span "wait for it"]
      [:div#graph
       (oz/view-spec [:vega-lite spec])])))
