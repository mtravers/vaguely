(ns vaguely.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vaguely.blockly :as blockly]
   [vaguely.config :as config]
   vaguely.views
   vaguely.data
   vaguely.library
   [vaguely.vega :as vega]
   ))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  #_
  (reagent/render [views/svg-pane]
    (.getElementById js/document "drawing"))
  ;; turned off for now
  ;; TODO put under an expander or something

  (reagent/render [vega/render]
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
   {}))
