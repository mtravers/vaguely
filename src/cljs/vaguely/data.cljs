(ns vaguely.data
  (:require [re-frame.core :as rf]
            [vaguely.canned :as canned]
            [vaguely.api :as api]
            )
  )

(def source "https://forge.scilab.org/index.php/p/rdataset/source/file/master/csv/datasets/trees.csv")
(def covid "https://covidtracking.com/data/download/all-states-history.csv") ;20K records, a bit much

(def poverty "https://raw.githubusercontent.com/owid/owid-datasets/master/datasets/Absolute%20number%20of%20people%20in%20poverty%20-%20OWID%20based%20on%20World%20Bank%20(Povcal)%20(2015)/Absolute%20number%20of%20people%20in%20poverty%20-%20OWID%20based%20on%20World%20Bank%20(Povcal)%20(2015).csv") ;good lord

(def movies "https://vega.github.io/editor/data/movies.json")




(def data-color "#e09f3e")            ;TODO elsewhere

(defn options
  [key-list]
  (mapv (juxt name name) key-list))

(defn blockdefs
  []
  (cons
   {:type "data-url"
    :output "data"
    :colour data-color
    :message0 "name %1"
    :args0 [{:name "name"
             :type "field_input"}]
    :message1 "url %1"
    :args1 [{:name "url"
             :type "field_input"
             :spellcheck false}]
    ;; Hardly ever used; do it another way
    ;; :message1 "format %1"
    ;; :args1 [{:name "format"
    ;;          :type "field_dropdown"
    ;;          :options (options [:inferred :csv :tsv :json])}] ;TODO  :excel
    }
   ;; TODO add format-specific blocks "json-data-url" eg
   (canned/blockdefs)))

(defmulti block-data (fn [block] (:type block)))

(defmethod block-data "data-url" [block]
  (let [url (get-in block [:children "url"])
        ;; format (get-in block [:children "format"])
        ]
    (rf/dispatch [:get-data-url url])
    @(rf/subscribe [:data])
    ))
  
(defmethod block-data "infertility" [block]
  (rf/dispatch [:set-data (canned/datasets "infertility")])
  @(rf/subscribe [:data]))

(defmethod block-data "trees" [block]
  (rf/dispatch [:set-data (canned/datasets "trees")])
  @(rf/subscribe [:data]))

(defmethod block-data "iris" [block]
  (rf/dispatch [:set-data (canned/datasets "iris")])
  @(rf/subscribe [:data]))

(rf/reg-event-db
 :get-data-url
 (fn [db [_ url format]]
   ;; TODO Possible Screw case where we change format, need to do update maybe
   (when-not (= (:data-url db) url)
     (rf/dispatch [:info (str "Reading from " url)])
     (api/ajax-get "/api/data"
                   {:url-params {:url url :format format}
                    :handler (fn [resp]
                               (let [data (:body resp)]
                                 (rf/dispatch [:info (str (count data) " records read from " url)])
                                 (rf/dispatch [:set-data data])))}))
   (assoc db :data-url url)))

(rf/reg-event-db
 :set-fields
 (fn [db [_ fields]]
   (assoc db :data-fields fields)))

;;; Extremely kludgey trick to allow field settings to be restored even when the query results aren't present
(defonce fake-fields (atom [:?]))

(rf/reg-event-db
 :set-data
 (fn [db [_  data]]
   (reset! fake-fields nil)
   (assoc db
          :data data
          :data-fields (sort (keys (first data))))))

(rf/reg-sub :data-fields
            (fn [db _]
              (:data-fields db)))

(rf/reg-sub :data
            (fn [db _]
              (:data db)))

(defn data-url
  [name url]
  [:block "data-url" {}
   [:field "name" name]
   [:field "url" url]])

(defn data-urls
  []
  (map (partial apply data-url)
       [["movies" movies]
        ["gapminder" "https://gist.github.com/justimchung/9e9324667c847d3c8c2f0bdec3c180dd/raw/0299cac8c5cd20a73be8bc3dafbf8e1f15ca6020/gapminder.csv"]
        ["OWID poverty" "https://raw.githubusercontent.com/owid/owid-datasets/master/datasets/Absolute%20number%20of%20people%20in%20poverty%20-%20OWID%20based%20on%20World%20Bank%20(Povcal)%20(2015)/Absolute%20number%20of%20people%20in%20poverty%20-%20OWID%20based%20on%20World%20Bank%20(Povcal)%20(2015).csv"]
        ["Cars" "https://vega.github.io/vega-lite/data/cars.json"]
        ["Seattle Weather" "https://vega.github.io/vega-lite/data/seattle-weather.csv"]
        ]))

(defn toolbox
  []
  `[:category "Data" {:colour ~data-color}
    ~@(data-urls)
    [:block "data-url" {}
     ]
    ~@(canned/toolbox)])
