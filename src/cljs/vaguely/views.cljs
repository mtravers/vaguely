(ns vaguely.views
  (:require
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
