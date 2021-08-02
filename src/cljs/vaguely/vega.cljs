(ns vaguely.vega
  (:require [vaguely.data :as data] 
            [org.parkerici.multitool.core :as u]
            [re-frame.core :as rf]
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
      {:mark {:type (get-in block [:children "mark"])
              :tooltip {:content "data"}}
       :encoding (vega-spec (get-in block [:children "encoding"]))
       :height default-height
       :width default-width
       :data {:values (data/vega-spec data-block)}
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

;;; Terminates recursion down :next chain
(defmethod vega-spec nil [_block]
  {})

(defn generate-vega-spec
  []
  (let [; data @(rf/subscribe [:data])    ;TOOD get data from block
        blocks @(rf/subscribe [:compact-all])
        vega-block (u/something #(= "layer" (:type %)) blocks)
        spec (vega-spec vega-block)]
    spec))

(defn render
  "React component showing the graph"
  []
  (when-let [spec (generate-vega-spec)]
    [:div#graph
     (oz/view-spec [:vega-lite spec])]))
