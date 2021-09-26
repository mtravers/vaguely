(ns vaguely.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vaguely.blockly :as blockly]
   [vaguely.config :as config]
   [vaguely.views :as views]
   vaguely.data
   [org.parkerici.multitool.cljscore :as cljsu]
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(rf/reg-sub :view
            (fn [db _]
              (:view db)))

;;; TODO Error/info probably should be unified

(rf/reg-sub :error
            (fn [db _]
              (:error db)))

(rf/reg-event-db
 :error
 (fn [db [_ error]]
   (assoc db :error error)))

(rf/reg-sub :info
            (fn [db _]
              (:info db)))

(rf/reg-event-db
 :info
 (fn [db [_ msg]]
   (assoc db :info msg)))

(rf/reg-event-db
 :open-url
 (fn [db [_ url]]
   (.open js/window url "_blank")
   db))




(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (reagent/render [views/rh-pane]
    (.getElementById js/document "vega")))

(defn re-frame-init []
  (rf/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

(defn- params-init
  []
  (when-let [library (:library (cljsu/url-params))]
    ;; TODO this flashes the usual welcome page, which is bad UX, should hide/replace it
    (rf/dispatch [:retrieve-by-uuid library])))


(defn ^:export init []
  (re-frame-init)
  (blockly/init)
  (params-init))

(defn figwheel-hook []
  (blockly/update-blockly)
  )

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   {:view :vega}))
