(ns vaguely.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vaguely.blockly :as blockly]
   [vaguely.views :as views]
   [vaguely.config :as config]
   vaguely.data
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  ;; TODO vega pane
  #_
  (reagent/render [views/svg-pane]
    (.getElementById js/document "drawing"))
  ;; turned off for now
  ;; TODO put under an expander or something
  #_
  (reagent/render [views/debug-pane]
    (.getElementById js/document "debug")))

(defn re-frame-init []
  (rf/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

(defn init []
  (re-frame-init)
  (blockly/init))

(defn figwheel-hook []
  (blockly/update-blockly)
  )

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   {}))
