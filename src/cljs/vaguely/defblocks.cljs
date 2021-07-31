(ns vaguely.defblocks
  (:require [org.parkerici.multitool.core :as u]
            [re-frame.core :as rf]
            [oz.core :as oz]
            ))

;;; Block definitions
;;; Stolen from enflame

(defn options
  [key-list]
  (mapv (juxt name name) key-list))

;;; Extremely kludgey trick to allow field settings to be restored even when the query results aren't present
(defonce fake-fields (atom [:?]))

(defn field-options
  "Compute the options for field dropdown at runtime. Value is js array of arrayus"
  []
  (let [cols @(rf/subscribe [:display-columns])
        cols (if (empty? cols)
               @fake-fields
               cols)] 
    (to-array (map #(to-array [(name %) (name %)]) cols))))

(def marks
  [:point :bar :line :tick :arc :area :boxplot])

(def attributes                         ;aka channels. There are more; these seemed like the most useful
  [:x :y :size :color :opacity :shape :theta :radius :strokeWidth :facet])

;;; Count is special (no underlying field) so has its own block. See https://vega.github.io/vega-lite/docs/aggregate.html#ops
;;; for complete set, these are the ones that seemed most useful.
(def aggregates
  [:valid :missing :distinct :median :mean :variance :stdev :sum :product :min :max])

(defn visualize-toolbox
  []
  [:category "Visualize" {}
   [:block "layer" {}
    ;; For some reason displayed order is inverse
    [:value "encoding" [:block "count_encoding" {}
                        [:field "attribute" "size"]]]
    [:value "encoding" [:block "encoding" {}
                        [:field "attribute" "y"]]]
    [:value "encoding" [:block "encoding" {}
                        [:field "attribute" "x"]]]

    ]
   [:block "encoding"]
   [:block "count_encoding" {} [:field "attribute" "size"]]
   [:block "aggregate_encoding"]
   ])

(def layer-color "#9e2a2b")
(def encoding-color "#407492")

(defn graph-blockdefs
  "Visualization blocks"
  []
  (list 
   {:type "layer"
    :colour layer-color
    :message0 "mark %1"
    :args0 [{:type "field_dropdown"
             :name "mark"
             :options (options marks)}]
    :message1 "encodings %1"
    :args1 [{:type "input_statement"
             :name "encoding"
             #_ :check #_ (str (name kind) "_constraint")}]
    :message2 "data %1"                 ;this is only for show at the moment
    :args2 [{:type "input_value"
             :name "data"
             }] 
    ;; Not really a query builder, but used for text gen dispatch
    :query-builder :query-builder-layer
    }
   {:type "encoding"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1 field %2 type %3" ;TODO axis, scale...
    :args0 [{:type "field_dropdown"
             :name "attribute"
             :options (options attributes)
             }
            {:type "field_dropdown" 
             :name "field"
             :options field-options
             }
            {:type "field_dropdown"
             :name "type"
             :options (options [:nominal :ordinal :quantitative]) ;TODO derive dynamically from data
             }
            ]}
   {:type "count_encoding"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1 count" ;TODO axis, scale...
    :args0 [{:type "field_dropdown"
             :name "attribute"
             :options (options attributes)
             }
            ]}
   {:type "aggregate_encoding"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1 field %2 %3" ;TODO axis, scale...
    :args0 [{:type "field_dropdown"
             :name "attribute"
             :options (options attributes)
             }
            {:type "field_dropdown" 
             :name "field"
             :options field-options
             }
            {:type "field_dropdown" 
             :name "aggregate"
             :options (options aggregates)}]}

   ))


;;; see views/render
(defn- flatten-row [row]
  (u/map-values (fn [v] (cond (map? v)
                              (or (:label/label v) (:db/id v))
                              (sequential? v)
                              (map #(or (:label/label %) (:db/id %)) v)
                              :else
                              v))
                row))

(defn- flatten-data [data]
    (map flatten-row data))

;;; Not working yet
(defn pop-out-button
  [id]
  [:button {:on-click #(let [elt (.getElementById js/document id)
                             content (.-innerHTML elt)
                             new-window (.open js/window)]
                         (.write (.-document new-window) content))}
   "open"])


(def shape-color "#35618f")             ;color for shape blocks

;;; very unClojurism, shoot me
(def standard-colors (atom '( "green" "red" "blue" "orange" "purple" "cyan")))

(defn next-color []
  (ffirst (swap-vals! standard-colors pop)))

(defn standard-shape-args []
  [{:name :fill :type :colour :default (next-color)}
   {:name :opacity :type :number :default 0.6}])

(defn shape-block [name args]
  {:type name
   :output :shape
   :category :shapes
   :colour shape-color
   :args (concat args (standard-shape-args))})

(defn number-block [name]
  {:type name
   :message0 name
   :output :number
   :colour "%{BKY_MATH_HUE}"})

(def blocks
  (graph-blockdefs))

(defn block-defs []
  blocks)

;;; ⩓⩔⩓ toolbox ⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔

;;; TODO a lot of this is unused

(defmulti default-block (fn [type default] type))

(defmethod default-block :default [type default]
  [:block "math_number" {} 
   [:field "NUM" default]]
  )

(defmethod default-block :number [type default]
  [:block "math_number" {} 
   [:field "NUM" default]]
  )

(defmethod default-block :colour [type default]
  [:block "colour_picker" {}
   [:field "COLOUR" default]])

(defmethod default-block :String [type default]
  [:block "text" {}
   [:field "TEXT" default]])

(defn toolbox-item [{:keys [type args] :as grapheme}]
  `[:block ~(name type) {}
    ~@(mapv (fn [arg]
              (let [{:keys [default type] :as arg} (if (map? arg) arg {:name arg})]
                `[:value ~(name (:name arg))
                  ~@(when default
                      [(default-block type default)]
                      )]))
            args)])

(def block-map (zipmap (map :type blocks) blocks))

(defn cat-blocks [cat]
  (mapv toolbox-item (filter #(= cat (:category %)) blocks)))

(defn toolbox-def []
  `[:toolbox
    ~(visualize-toolbox)])
