(ns vaguely.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]
   [vaguely.blockly :as blockly]
   [vaguely.views :as views]
   [vaguely.config :as config]
   vaguely.data
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

(defn init []
  (re-frame-init)
  (blockly/init))

(defn figwheel-hook []
  (blockly/update-blockly)
  )

(def trees
  '({:id 1, :Girth 8.3, :Height 70, :Volume 10.3}
    {:id 2, :Girth 8.6, :Height 65, :Volume 10.3}
    {:id 3, :Girth 8.8, :Height 63, :Volume 10.2}
    {:id 4, :Girth 10.5, :Height 72, :Volume 16.4}
    {:id 5, :Girth 10.7, :Height 81, :Volume 18.8}
    {:id 6, :Girth 10.8, :Height 83, :Volume 19.7}
    {:id 7, :Girth 11, :Height 66, :Volume 15.6}
    {:id 8, :Girth 11, :Height 75, :Volume 18.2}
    {:id 9, :Girth 11.1, :Height 80, :Volume 22.6}
    {:id 10, :Girth 11.2, :Height 75, :Volume 19.9}
    {:id 11, :Girth 11.3, :Height 79, :Volume 24.2}
    {:id 12, :Girth 11.4, :Height 76, :Volume 21}
    {:id 13, :Girth 11.4, :Height 76, :Volume 21.4}
    {:id 14, :Girth 11.7, :Height 69, :Volume 21.3}
    {:id 15, :Girth 12, :Height 75, :Volume 19.1}
    {:id 16, :Girth 12.9, :Height 74, :Volume 22.2}
    {:id 17, :Girth 12.9, :Height 85, :Volume 33.8}
    {:id 18, :Girth 13.3, :Height 86, :Volume 27.4}
    {:id 19, :Girth 13.7, :Height 71, :Volume 25.7}
    {:id 20, :Girth 13.8, :Height 64, :Volume 24.9}
    {:id 21, :Girth 14, :Height 78, :Volume 34.5}
    {:id 22, :Girth 14.2, :Height 80, :Volume 31.7}
    {:id 23, :Girth 14.5, :Height 74, :Volume 36.3}
    {:id 24, :Girth 16, :Height 72, :Volume 38.3}
    {:id 25, :Girth 16.3, :Height 77, :Volume 42.6}
    {:id 26, :Girth 17.3, :Height 81, :Volume 55.4}
    {:id 27, :Girth 17.5, :Height 82, :Volume 55.7}
    {:id 28, :Girth 17.9, :Height 80, :Volume 58.3}
    {:id 29, :Girth 18, :Height 80, :Volume 51.5}
    {:id 30, :Girth 18, :Height 80, :Volume 51}
    {:id 31, :Girth 20.6, :Height 87, :Volume 77}))

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   {:data trees}))
