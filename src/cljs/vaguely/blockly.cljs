(ns vaguely.blockly
  (:require [org.parkerici.blockoid.core :as bo]
            [org.parkerici.multitool.core :as u]
            cljsjs.blockly.msg.en
            [vaguely.defblocks :as defblocks]
            [vaguely.vega :as vega]
            [vaguely.data :as data]
            [re-frame.core :as rf]
            [clojure.data.xml :as xml]
            )
  )

;;; standard Blockly init
(defn init
  []
  (bo/define-blocks (defblocks/block-defs))
  (bo/define-workspace
    "blocklyDiv"
    (bo/toolbox (defblocks/toolbox-def))
    ;; These might make sense
    {:zoom {:controls true :wheel true :startScale 1.0 :maxScale 3 :minScale 0.3 :scaleSpeed 1.2}
     :renderer "thrasos"}
    (fn [e]
      (rf/dispatch [:blockly-event e]))
    )
  (bo/auto-resize-workspace "blocklyArea")
  (.hide (.getFlyout @bo/workspace))    ;hide the flyout which for some reason gets exposed 
  )

                                        ;TODO thought I fixed this...

(defn update-blockly []
  (bo/define-blocks (defblocks/block-defs))
  (bo/update-toolbox (bo/toolbox (defblocks/toolbox-def))))

(rf/reg-sub
 :compact-all
 (fn [db _]
   (:compact-all db)))

(rf/reg-event-db
 :blockly-event
 (fn [db _]
   (let [struct (bo/workspace-selected-xml)
         compact (bo/compact struct)
         compact-all (bo/compact (bo/workspace-xml))
         ]
     (when-not (empty? compact-all)
       (rf/dispatch [:choose-vega-tab :rh]))
     (assoc db
            :struct struct
            :compact compact
            :compact-all compact-all
            ))))

(defn save-fake-fields!
  [xml-string]
  (let [fake-fields (->> xml-string
                         xml/parse-str
                         (u/walk-collect #(when (= "field" (get-in % [:attrs :name]))
                                            (keyword (first (get % :content))))))]
    ;; This has to go through a special mechanism so the fields are there before blocks get built
    ;; This is undone when actual data is available
    (reset! data/fake-fields fake-fields)
    ))

(defn restore-from-saved
  [xml-string]
  (save-fake-fields! xml-string)
  (let [dom (.textToDom js/Blockly.Xml xml-string)]
    (bo/clear-workspace)
    (.domToWorkspace js/Blockly.Xml dom @bo/workspace)))
