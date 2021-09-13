(ns vaguely.defblocks
  (:require [vaguely.data :as data]
            [org.parkerici.multitool.core :as u]
            [clojure.string :as str]
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
  [:point :bar :line :tick :arc :rect :area :boxplot :text])

(def attributes                         ;aka channels. There are more; these seemed like the most useful
  [:x :y :size :shape :color :opacity :column :theta :radius :strokeWidth :facet :text :tooltip
   :x2 :y2                              ;would be good to turn these off when they don't apply (same goes for others)
   ])

;;; for complete set, these are the ones that seemed most useful.
(def aggregates
  [:count :valid :missing :distinct :median :mean :variance :stdev :sum :product :min :max])

(def layer-color "#9e2a2b")
(def encoding-color "#407492")
(def math-color "#3f943f")

;;; Abstractions for creating encoding attributes
;;; TODO steal arg building form mrfrieze/src/cljc/mrfrieze/defblocks.cljc

(defn encoding-attribute
  [name]
  {:type (str "encoding_" name)
   :colour encoding-color
   :message0 (str (str/replace name #"_" " ") " %1")
   :previousStatement "encoding_att"
   :nextStatement "encoding_att"
   })

(defn encoding-dropdown-attribute
  [name attribute-options]
  (-> (encoding-attribute name)
      (assoc :args0 [{:type "field_dropdown" 
                      :name name        ;should probably be a constant name, but this is working now
                      :options attribute-options
                      }])))

(defn encoding-string-attribute
  [name]
  (-> (encoding-attribute name)
      (assoc :args0 [{:type "field_input" 
                      :name "value"
                      }])))

(defn encoding-number-attribute
  [name]
  (-> (encoding-attribute name)
      (assoc :args0 [{:type "input_value" 
                      :name "value"
                      :check "Number"
                      }])))

(defn encoding-filter-attribute
  [name]
  (-> (encoding-attribute name)
      ;; TODO needs a nice abstraction
      (assoc :message0 "filter to %1 %2"
             :args0 [{:type "field_dropdown" 
                      :name "operator"
                      :options '[[< "lt"] [<= "lte"] [= "equal"] [=> "gte"] [> "gt"]]
                      }
                     {:type "input_value"
                      :name "value"
                      :check "Number"
                     }
                     ])))

(defn encoding-sort-attribute
  [name]
  (-> (encoding-attribute name)
      (assoc :message0 "sort by %1 %2"
             :args0 [{:type "field_dropdown" 
                      :name "field"
                      :options field-options
                      }
                     {:type "field_dropdown" 
                      :name "order"
                      :options (options ["ascending" "descending"])
                      }
                     ])))

;;; Substitute for math_number which has wrong color
(defn number
  []
  {:type "number"
   :output "Number"
   :colour math-color
   :message0 "%1"
   :args0 [{:type "field_input"
            :name "NUM"}]})

(defn slider
  []
  {:type "slider"
   :colour math-color
   :message0 "slider %1 [%2, %3]"
   :output "Number"
   :args0 [{:type "field_input" 
            :name "name"}
           {:type "field_input" 
            :name "min"}
           {:type "field_input" 
            :name "max"}
           ]})

;;; Obso, here so library can loadd
(defn encoding-slider
  [name]
  (-> (encoding-attribute name)
      ;; TODO needs a nice abstraction
      (assoc :message0 "slider %1 [%2, %3]"
            :args0 [{:type "field_input" 
                      :name "name"}
                     {:type "field_input" 
                      :name "min"}
                     {:type "field_input" 
                      :name "max"
                     }
            ])))


(defn expression
  []
  {:type "expression"
   :colour math-color
   :message0 "expression %1"
   :output "Number"
   :args0 [{:type "field_input" 
            :name "expr"}
           ]})

(defn graph-blockdefs
  "Visualization blocks"
  []
  (list 

   {:type "layer"
    :colour layer-color
    :previousStatement "layer"
    :nextStatement "layer"
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

   {:type "layers"
    :colour layer-color

    :message0 "data %1"
    :args0 [{:type "input_value"
             :name "data"
             }]

    :message1 "layers %1"
    :args1 [{:type "input_statement"
             :name "layers"
             }]

    :message2 "encodings %1"
    :args2 [{:type "input_statement"
             :name "encoding"
             #_ :check #_ (str (name kind) "_constraint")}]
    }

   {:type "regression_layer"
    :colour layer-color
    :previousStatement "layer"
    :nextStatement "layer"
    :message0 "%1 regression"
    :args0 [{:type "field_dropdown" 
            :name "method"
            :options (options [:linear :log :exp :pow :quad :poly :loess])
             }]


    :message1 "independent %1"
    :args1 [{:type "input_statement"
             :name "independent"
             }]
    :message2 "dependent %1"
    :args2 [{:type "input_statement"
             :name "dependent"
             }]
    }

   ;; V1

   {:type "encoding"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1 field %2 type %3" 
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

   ;; V2

   {:type "encoding2"
    :colour encoding-color
    :previousStatement "encoding"
    :nextStatement "encoding"
    :message0 "attribute %1 type %2"
    :args0 [{:type "field_dropdown"
             :name "attribute"
             :options (options attributes)
             }
            {:type "field_dropdown"
             :name "type"
             :options (options [:nominal :ordinal :quantitative :temporal])
             }]
    :message1 "%1"
    :args1 [{:type "input_statement"
             :name "encoding_attribute"
             #_ :check #_ (str (name kind) "_constraint")}]
    }


   (encoding-dropdown-attribute "field" field-options)


   ;; TODO temp here for old library compat
   (encoding-dropdown-attribute "type" (options [:nominal :ordinal :quantitative :temporal]))

   (encoding-dropdown-attribute "scale"  (options [:linear :log :symlog :pow :sqrt             ;for continuous vars (TODO adjust options). Also :pow requires argument?
                                                   :time :utc]))

   (encoding-number-attribute "value")
   (encoding-string-attribute "title")

   (encoding-filter-attribute "filter")
   (encoding-sort-attribute "sort")

   (encoding-number-attribute "domain_min")
   (encoding-number-attribute "domain_max")
   (encoding-number-attribute "range_min")
   (encoding-number-attribute "range_max")

   (encoding-string-attribute "expression")

   (number)
   (slider)
   (encoding-slider "slider")                    ;obso
   (expression)                                  ;think this doesn't work

   (-> (encoding-dropdown-attribute "aggregate" (options aggregates))
       (assoc :message0 "aggregated by %1"))

   ))


;;; Not working yet
(defn pop-out-button
  [id]
  [:button {:on-click #(let [elt (.getElementById js/document id)
                             content (.-innerHTML elt)
                             new-window (.open js/window)]
                         (.write (.-document new-window) content))}
   "open"])


(def blocks
  (concat (graph-blockdefs)
          (data/blockdefs)))

(defn block-defs []
  blocks)

;;; ⩓⩔⩓ toolbox ⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔⩓⩔

;;; TODO this probably wants to move to Blockoid.
;;; TODO a lot of this is unused

(defmulti default-block (fn [type default] type))

(defmethod default-block :default [type default]
  [:block "number" {} 
   [:field "NUM" default]]
  )

(defmethod default-block :number [type default]
  [:block "number" {} 
   [:field "NUM" default]]
  )

;;; TODO currently unused, would be cool to be able to use this for color constants (maybe domain and range!)
(defmethod default-block :colour [type default]
  [:block "colour_picker" {}
   [:field "COLOUR" default]])

(defmethod default-block :String [type default]
  [:block "text" {}
   [:field "TEXT" default]])

(defn toolbox-item [{:keys [type args]}]
  `[:block ~(name type) {}
    ~@(mapv (fn [arg]
              (let [{:keys [default type] :as arg} (if (map? arg) arg {:name arg})]
                `[:value ~(name (:name arg))
                  ~@(when default
                      [(default-block type default)]
                      )]))
            args)])

(def block-map (zipmap (map :type blocks) blocks))

(defn num-toolbox
  [type default]
  [:block type {} [:value "value" [:block "number" {} [:field "NUM" default]]]]
  )

(defn toolbox-def
  []
  `[:toolbox
    [:category "Marks / Layers" {:colour ~layer-color}

     [:block "layer" {}
      ;; For some reason displayed order is inverse
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "size"]
                          [:field "type" "quantitative"]
                          [:value "encoding_attribute" [:block "encoding_aggregate" {} ]]
                          ]]
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "y"]
                          [:field "type" "quantitative"]
                          [:value "encoding_attribute" [:block "encoding_field"]]
                          ]]
      [:value "encoding" [:block "encoding2" {}
                          [:field "attribute" "x"]
                          [:field "type" "quantitative"]
                          [:value "encoding_attribute" [:block "encoding_field"]]]]

      ]
     [:block "layer"]


     [:block "layers"]
     [:block "regression_layer"]
     ]
    [:category "Encodings" {:colour ~encoding-color}

     [:block "encoding2"]
     [:block "encoding2" {}
      [:field "type" "quantitative"]
      [:value "encoding_attribute" [:block "encoding_field"]]
      ]
     [:block "encoding_field"]
     [:block "encoding_aggregate"]
     [:block "encoding_scale"]

     ~(num-toolbox "encoding_value" 23)

     ~(num-toolbox "encoding_domain_min" 0)
     ~(num-toolbox "encoding_domain_max" 100)
     ~(num-toolbox "encoding_range_min" 0)
     ~(num-toolbox "encoding_range_max" 100)

     [:block "encoding_title"]
     [:block "encoding_filter"]
     [:block "encoding_sort"]
     [:block "encoding_expression"]
;     [:block "encoding_slider"]

     ]
    ~(data/toolbox)
    ;; TODO Change to values and add color block
    [:category "Numbers" {:colour ~math-color}         
     [:block "number"]
     [:block "slider"]
     ;; Nah
     #_ [:block "expression"]
     ]
    [:category "Library" {}
     [:button "Browse" [:browse]]
     [:button "Save" [:save]]]
    ])
