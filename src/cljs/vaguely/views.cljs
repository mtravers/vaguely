(ns vaguely.views
  (:require
   [vaguely.library :as library]
   [vaguely.vega :as vega]
   [clojure.pprint :as pprint]
   [re-frame.core :as rf]
   ))

;;; TODO a lot of this is unused stuff from mr frieze, delete it or hook it up

(defn compact-pane []
  [:pre
   (with-out-str (pprint/pprint @(rf/subscribe [:compact])))])

(defn compiled-pane []
  [:pre
   (with-out-str (pprint/pprint @(rf/subscribe [:compiled])))])

(defn debug-pane []
  [:div {:style {:display "flex"}}
   [compact-pane]
   [compiled-pane]])

(defn mouse-coords [e]
  (let [target (.getElementById js/document "svgc")
        target-rect (.getBoundingClientRect target)
        xoff (- (.-scrollLeft target) (.-left target-rect))
        yoff (- (.-scrollTop target) (.-top target-rect))]
    [(+ (.-clientX e) xoff)
     (+ (.-clientY e) yoff)]))

;;; Not really used...although could be useful for actual big data
(defn full-screen [elem-name]
  (let [elem (.getElementById js/document elem-name)]
    ;; JFC. From https://www.w3schools.com/howto/howto_js_fullscreen.asp
    ;; Note the subtly different capitalizations (may not be needed, first clause works with chrome at least)
    (cond (.-requestFullscreen elem)
          (.requestFullscreen elem)
          (.-mozRequestFullScreen elem)
          (.mozRequestFullScreen elem)
          (.-webkitRequestFullscreen elem)
          (.webkitRequestFullscreen elem)
          (.-msRequestFullscreen elem)
          (.msRequestFullscreen elem))))

;;; TODO a version of this that made the svg page the full browser window, but not full screen
;;; Would be useful but require an entirely different mechanism.

(rf/reg-event-fx
 :fullscreen
 (fn [_ _]
   (full-screen "drawing")
   {}))

(defn about-pane
  []
  [:div
   [:h3 "About Vaguely"]
   [:p
    "Vaguely is a tool for exploratory data visualization, based on "
    [:a {:href "https://vega.github.io/vega-lite/"} "Vega-Lite"]
    ", a high-level grammar of interactive graphics. Vaguely is a partial translation of that grammar into a Scratch-like blocks interface, making it easier to experiment with different visualizations."]
   [:p "To get started, try selecting an example from the "
    [:a {:href "#" :on-click #(rf/dispatch [:browse])} "library"] "."
    ]
   [:h4 "Credits"]
   [:ul
    [:li "Author: " [:a {:href "http://hyperphor.com"} "Mike Travers"]]
    [:li "Support: " [:a {:href "https://parkerici.org"} "Parker Institute for Cancer Immunotherapy"]]
    [:li "Source: " [:a {:href "https://github.com/mtravers/vaguely"} "Github"]]
    [:li "Components: Vega-Lite, Blockly, Clojure"]]
   [:p "Copyright © Hyperphor 2021"]])


;;; Should be in some kind of web utils package oh well

(rf/reg-sub
 :active-tab
 (fn [db [_ id]]
   (get-in db [:active-tab id])))

(rf/reg-event-db
 :choose-tab
 (fn [db [_ id tab]]
   (assoc-in db [:active-tab id] tab)))

;;; Special-purpose hack: if user changes blocks, show either graph or spec pane
(rf/reg-event-db
 :choose-vega-tab
 (fn [db [_ id]]
   (if (contains? #{"Graph" "Spec"} (get-in db [:active-tab id]))
     db
     (assoc-in db [:active-tab id] "Graph"))))

(defn tabs
  [id tabs]
  (let [active (or @(rf/subscribe [:active-tab id]) "About")]
    [:div
     [:ul.nav.nav-tabs
      (for [[name view] tabs]
        ^{:key name}
        [:li.nav-item
         (if name
           [:a.nav-link {:class (when (= name active) "active")
                         :on-click #(rf/dispatch [:choose-tab id name])}
            name]
           [:a.nav-link.disabled.vtitle view])])]
     ((tabs active))]))

(defn error
  []
  [:div.alert-danger 
   [:button {:type "button" :title "Close"
             :class "close"
             :on-click #(rf/dispatch [:error nil])}
    [:i {:class "material-icons"} "close"]]
   [:pre {:style {:white-space "normal"}} ;yes this is how you get wrapping
    (str @(rf/subscribe [:error]))]])

(defn info
  []
  [:div.alert-info 
   [:button {:type "button" :title "Close"
             :class "close"
             :on-click #(rf/dispatch [:info nil])}
    [:i {:class "material-icons"} "close"]]
   (str @(rf/subscribe [:info]))])

(defn rh-pane
  []
  [:div
   (when @(rf/subscribe [:info])
     [info])
   (when @(rf/subscribe [:error])
     [error])
   (tabs :rh
         {nil "Vaguely"
          "About" about-pane
          "Graph" vega/render
          "Spec" vega/spec-pane
          "Library" library/browse})])


  
