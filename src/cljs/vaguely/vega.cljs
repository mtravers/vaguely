(ns vaguely.vega
  (:require [vaguely.data :as data] 
            [vaguely.views :as views]
            [org.parkerici.multitool.core :as u]
            [re-frame.core :as rf]
            [clojure.walk :as walk]
            [clojure.pprint :as pprint]
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
  (let [data-block (get-in block [:children "data"])]
    {:mark {:type (get-in block [:children "mark"])
            :tooltip {:content "data"}
            :clip true                  ;Necessary for domain_min/max to work as expected.
            }
     :encoding (vega-spec (get-in block [:children "encoding"]))
     ;; The default default is too small...this is not always right but better than nothing
     :height default-height
     :width default-width
     :data (when data-block {:values (data/block-data data-block)})
     }))

(defn block-seq
  "Turns blocks linked by :next into a list" 
  [block]
  (when block
    (cons block (block-seq (get-in block [:children :next])))))

(defmethod vega-spec "layers" [block]
  (let [data-block (get-in block [:children "data"])
        layers-blocks (block-seq (get-in block [:children "layers"]))
        encoding-blocks (get-in block [:children "encoding"])
        ]
    {:layer (map vega-spec layers-blocks)
     :data (when data-block {:values (data/block-data data-block)})
     :encoding (when encoding-blocks (vega-spec encoding-blocks))
     }))

;;; TODO add the regression info labels (see https://vega.github.io/vega-lite/docs/regression.html)
;;; Loess is different that the rest 
(defmethod vega-spec "regression_layer" [block]
  (let [method (get-in block [:children "method"])
        dependent (vega-spec (get-in block [:children "dependent"]))
        independent (vega-spec (get-in block [:children "independent"]))]
    {:mark {:type :line
            :color "red"},
     :transform [{(if (= method :loess) :loess :regression) (:field (first (vals dependent)))
                  :on (:field (first (vals independent)))
                  :method (if (= method :loess) nil method)}]
     :encoding
     (merge dependent independent)}
    ))

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
  (u/merge-recursive
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

(defmethod vega-spec "encoding_title" [block]
  (merge
   {:title (get-in block [:children "value"]) }
   (vega-spec (get-in block [:children :next]))))

;;; Want to DRY this but hard because macros are hard in cljs

(defmethod vega-spec "encoding_domain_min" [block]
  (u/merge-recursive
   {:scale {:domainMin (u/coerce-numeric (get-in block [:children "value"])) }}
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_domain_max" [block]
  (u/merge-recursive
   {:scale {:domainMax (u/coerce-numeric (get-in block [:children "value"])) }}
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_range_min" [block]
  (u/merge-recursive
   {:scale {:rangeMin (u/coerce-numeric (get-in block [:children "value"])) }}
   (vega-spec (get-in block [:children :next]))))

(defmethod vega-spec "encoding_range_max" [block]
  (u/merge-recursive 
   {:scale {:rangeMax (u/coerce-numeric (get-in block [:children "value"])) }}
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
  (let [blocks @(rf/subscribe [:compact-all])
        vega-block (u/something #(contains? #{"layer" "layers"} (:type %)) blocks)]
    (-> vega-block
        vega-spec
        ;; Maybe other post vega-spec transforms
        repeat-transform)))


(defn render
  "React component showing the graph"
  []
  (try                                  ;this fails to get Vega errorss which happen frokm a render loop
    (let [spec (generate-vega-spec)]
      (if (empty? spec)
        (views/about-pane)
        [:div#graph
         (oz/view-spec [:vega-lite spec])]))
    (catch :default e
      (rf/dispatch [:error e]))))

(defn spec-pane
  []
  (let [spec (dissoc (generate-vega-spec) :data)] ;TODO can have data sources in inside elements
    [:pre {:style {:text-size "small"}}
     (when-not (empty? spec)
       (with-out-str (pprint/pprint spec)))
     ;; TODO Json version

     ]))
