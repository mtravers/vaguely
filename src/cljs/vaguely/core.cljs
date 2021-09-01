(ns vaguely.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vaguely.blockly :as blockly]
   [vaguely.config :as config]
   vaguely.views
   vaguely.data
   [vaguely.library :as library]
   [vaguely.vega :as vega]
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(rf/reg-sub :view
            (fn [db _]
              (:view db)))

(rf/reg-sub :error
            (fn [db _]
              (:error db)))

(rf/reg-event-db
 :error
 (fn [db [_ error]]
   (assoc db :error error)))

(rf/reg-event-db
 :open-url
 (fn [db [_ url]]
   (.open js/window url "_blank")
   db))

(defn error
  []
  [:div.alert-danger 
   [:button {:type "button" :title "Close"
             :class "close"
             :on-click #(rf/dispatch [:error nil])}
    [:i {:class "material-icons"} "close"]]
   [:pre {:style {:white-space "normal"}} ;yes this is how you get wrapping
    (str @(rf/subscribe [:error]))]])

(defn rh-pane
  []
  [:div
   (when @(rf/subscribe [:error])
     [error])
   (case @(rf/subscribe [:view])
     :vega [vega/render]
     :library [library/browse])
   [vega/spec-pane]
   ])


(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (reagent/render [rh-pane]
    (.getElementById js/document "vega")))

(defn re-frame-init []
  (rf/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

(defn ^:export init []
  (re-frame-init)
  (blockly/init))

(defn figwheel-hook []
  (blockly/update-blockly)
  )

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   {:view :vega}))
