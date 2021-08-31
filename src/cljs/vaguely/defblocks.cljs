(ns vaguely.defblocks
  (:require [vaguely.data :as data]
            [org.parkerici.multitool.core :as u]
            [re-frame.core :as rf]
            ))

;;; Block definitions
;;; Stolen from enflame

(defn options
  [key-list]
  (mapv (juxt name name) key-list))

(defn field-options
  "Compute the options for field dropdown at runtime. Value is js array of arrayus"
  []
  (let [cols (or @data/fake-fields
                 @(rf/subscribe [:data-fields]))
        cols (if (empty? cols) [:?] cols)
        ]
    (to-array (map #(to-array [(name %) (name %)]) cols))))

(def marks
  [:point :bar :line :tick :arc :area :boxplot :text])

(def attributes                         ;aka channels. There are more; these seemed like the most useful
  [:x :y :size :shape :color :opacity :column :theta :radius :strokeWidth :facet :text :tooltip
   :x2 :y2                              ;would be good to turn these off when they don't apply (same goes for others)
   ])

;;; for complete set, these are the ones that seemed most useful.
(def aggregates
  [:count :valid :missing :distinct :median :mean :variance :stdev :sum :product :min :max])

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
    :message1 "data %1"
    :args1 [{:type "input_value"
             :name "data"
             }]
    :message2 "encodings %1"
    :args2 [{:type "input_statement"
             :name "encoding"
             #_ :check #_ (str (name kind) "_constraint")}]
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
             :options (options [:nominal :ordinal :quantitative :temporal]) ;TODO derive dynamically from data
             }
            ]}

   ;; V2

   {:type "encoding2"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1"
    :args0 [{:type "field_dropdown"
             :name "attribute"
             :options (options attributes)
             }]
    :message1 "properties %1"
    :args1 [{:type "input_statement"
             :name "encoding_attribute"
             #_ :check #_ (str (name kind) "_constraint")}]
    }
   {:type "encoding_field"
    :colour encoding-color
    :message0 "field %1"
    :previousStatement "encoding_att"
    :nextStatement "encoding_att"
    :args0 [{:type "field_dropdown" 
             :name "field"
             :options field-options
             }]
    }
   {:type "encoding_type"
    :colour encoding-color
    :message0 "type %1"
    :previousStatement "encoding_att"
    :nextStatement "encoding_att"
    :args0 [{:type "field_dropdown"
             :name "type"
             :options (options [:nominal :ordinal :quantitative :temporal]) ;TODO derive dynamically from data
             }]
    }
   {:type "encoding_scale"
    :colour encoding-color
    :message0 "scale %1"
    :previousStatement "encoding_att"
    :nextStatement "encoding_att"
    :args0 [{:type "field_dropdown"
             :name "scale"
             :options (options [:linear :log :symlog :pow :sqrt             ;for continuous vars (TODO adjust options). Also :pow requires argument?
                                :time :utc
                                
                                ])
             }]
    }

   {:type "encoding_value"
    :colour encoding-color
    :message0 "value %1"
    :previousStatement "encoding_att"
    :nextStatement "encoding_att"
    :args0 [{:type "field_input"
             :name "value"
             }]
    }

   {:type "encoding_aggregate"
    :colour encoding-color
    :message0 "aggregated by %1"
    :previousStatement "encoding_att"
    :nextStatement "encoding_att"
    :args0 [{:type "field_dropdown" 
             :name "aggregate"
             :options (options aggregates)}
            ]
    }




   ;; V1 Aggregates

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


;;; Not working yet
(defn pop-out-button
  [id]
  [:button {:on-click #(let [elt (.getElementById js/document id)
                             content (.-innerHTML elt)
                             new-window (.open js/window)]
                         (.write (.-document new-window) content))}
   "open"])

#_
(defn number-block [name]
  {:type name
   :message0 name
   :output :number
   :colour "%{BKY_MATH_HUE}"})

(def blocks
  (concat (graph-blockdefs)
          (data/blockdefs)))

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

;;; TODO currently unused, would be cool to be able to use this for color constants (maybe domain and range!)
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

(defn toolbox-def
  []
  `[:toolbox
    [:category "Visualization" {}
     #_
     [:block "layer" {}
      ;; For some reason displayed order is inverse
      [:value "encoding" [:block "count_encoding" {}
                          [:field "attribute" "size"]]]
      [:value "encoding" [:block "encoding" {}
                          [:field "attribute" "y"]]]
      [:value "encoding" [:block "encoding" {}
                          [:field "attribute" "x"]]]

      ]
     [:block "layer" {}
      ;; For some reason displayed order is inverse
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "size"]
                          [:value "encoding_attribute" [:block "encoding_aggregate" {} ]]
                          ]]
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "y"]
                          [:value "encoding_attribute" [:block "encoding_type" {} [:field "type" "quantitative"]]]
                          [:value "encoding_attribute" [:block "encoding_field"]]
                          ]]
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "x"]
                          [:value "encoding_attribute" [:block "encoding_type" {} [:field "type" "quantitative"]]]
                          [:value "encoding_attribute" [:block "encoding_field"]]]]

      ]

     #_ [:block "encoding"]
     #_ [:block "count_encoding" {} [:field "attribute" "size"]]
     #_ [:block "aggregate_encoding"]

     [:block "encoding2"]
     [:block "encoding2" {}
      [:value "encoding_attribute" [:block "encoding_type" {} [:field "type" "quantitative"]]]
      [:value "encoding_attribute" [:block "encoding_field"]]
      ]
     [:block "encoding_field"]
     [:block "encoding_aggregate"]
     [:block "encoding_type"]           ;maybe flush, this has to be on all attributes
     [:block "encoding_scale"]
     [:block "encoding_value"]

     ]
    ~(data/toolbox)
    [:category "Library" {}
     [:button "Browse" [:browse]]
     [:button "Save" [:save]]]
    [:category "About" {}
     [:button "About" [:open-url "http://www.hyperphor.com/ammdi/pages/About-Vaguely.html"]]
     [:button "Source" [:open-url "https://github.com/mtravers/vaguely"]]
     ]
    ])
