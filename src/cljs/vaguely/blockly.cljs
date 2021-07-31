(ns vaguely.blockly
  (:require [org.parkerici.blockoid.core :as bo]
            cljsjs.blockly.msg.en
            [vaguely.defblocks :as defblocks]
            [re-frame.core :as rf]
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
  (prn :update)
  (bo/define-blocks (defblocks/block-defs))
  (bo/update-toolbox (bo/toolbox (defblocks/toolbox-def))))
