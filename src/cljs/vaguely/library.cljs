(ns vaguely.library
  (:require [vaguely.api :as api]
            [org.parkerici.blockoid.core :as bo]
            [clojure.string :as str]
            [re-frame.core :as rf]))

;;; From blockly/css.js . Supposed to be available at runtime as Blockly.Css.CONTENT, but that
;;; gets nulled out for some reason. This could be trimmed way back.
(def blockly-css
  [
  ".blocklySvg {",
    "background-color: #fff;",
    "outline: none;",
    "overflow: hidden;",  
    "position: absolute;",
    "display: block;",
  "}",

  ".blocklyWidgetDiv {",
    "display: none;",
    "position: absolute;",
    "z-index: 99999;", 
  "}",

  ".injectionDiv {",
    "height: 100%;",
    "position: relative;",
    "overflow: hidden;", 
    "touch-action: none",
  "}",

  ".blocklyNonSelectable {",
    "user-select: none;",
    "-moz-user-select: none;",
    "-ms-user-select: none;",
    "-webkit-user-select: none;",
  "}",

  ".blocklyWsDragSurface {",
    "display: none;",
    "position: absolute;",
    "top: 0;",
    "left: 0;",
  "}",
  
  ".blocklyWsDragSurface.blocklyOverflowVisible {",
    "overflow: visible;",
  "}",

  ".blocklyBlockDragSurface {",
    "display: none;",
    "position: absolute;",
    "top: 0;",
    "left: 0;",
    "right: 0;",
    "bottom: 0;",
    "overflow: visible !important;",
    "z-index: 50;", 
  "}",

  ".blocklyBlockCanvas.blocklyCanvasTransitioning,",
  ".blocklyBubbleCanvas.blocklyCanvasTransitioning {",
    "transition: transform .5s;",
  "}",

  ".blocklyTooltipDiv {",
    "background-color: #ffffc7;",
    "border: 1px solid #ddc;",
    "box-shadow: 4px 4px 20px 1px rgba(0,0,0,.15);",
    "color: #000;",
    "display: none;",
    "font-family: sans-serif;",
    "font-size: 9pt;",
    "opacity: .9;",
    "padding: 2px;",
    "position: absolute;",
    "z-index: 100000;", 
  "}",

  ".blocklyDropDownDiv {",
    "position: fixed;",
    "left: 0;",
    "top: 0;",
    "z-index: 1000;",
    "display: none;",
    "border: 1px solid;",
    "border-radius: 2px;",
    "padding: 4px;",
    "-webkit-user-select: none;",
  "}",

  ".blocklyDropDownContent {",
    "max-height: 300px;", 
    "overflow: auto;",
    "overflow-x: hidden;",
  "}",

  ".blocklyDropDownArrow {",
    "position: absolute;",
    "left: 0;",
    "top: 0;",
    "width: 16px;",
    "height: 16px;",
    "z-index: -1;",
    "background-color: inherit;",
    "border-color: inherit;",
  "}",

  ".blocklyDropDownButton {",
    "display: inline-block;",
    "float: left;",
    "padding: 0;",
    "margin: 4px;",
    "border-radius: 4px;",
    "outline: none;",
    "border: 1px solid;",
    "transition: box-shadow .1s;",
    "cursor: pointer;",
  "}",

  ".arrowTop {",
    "border-top: 1px solid;",
    "border-left: 1px solid;",
    "border-top-left-radius: 4px;",
    "border-color: inherit;",
  "}",

  ".arrowBottom {",
    "border-bottom: 1px solid;",
    "border-right: 1px solid;",
    "border-bottom-right-radius: 4px;",
    "border-color: inherit;",
  "}",

  ".blocklyResizeSE {",
    "cursor: se-resize;",
    "fill: #aaa;",
  "}",

  ".blocklyResizeSW {",
    "cursor: sw-resize;",
    "fill: #aaa;",
  "}",

  ".blocklyResizeLine {",
    "stroke: #515A5A;",
    "stroke-width: 1;",
  "}",

  ".blocklyHighlightedConnectionPath {",
    "fill: none;",
    "stroke: #fc3;",
    "stroke-width: 4px;",
  "}",

  ".blocklyPathLight {",
    "fill: none;",
    "stroke-linecap: round;",
    "stroke-width: 1;",
  "}",

  ".blocklySelected>.blocklyPath {",
    "stroke: #fc3;",
    "stroke-width: 3px;",
  "}",

  ".blocklySelected>.blocklyPathLight {",
    "display: none;",
  "}",

  ".blocklyDraggable {",
    "cursor: url(\"<<<PATH>>>/handopen.cur\"), auto;",
    "cursor: grab;",
    "cursor: -webkit-grab;",
  "}",

   ".blocklyDragging {",
    
    "cursor: url(\"<<<PATH>>>/handclosed.cur\"), auto;",
    "cursor: grabbing;",
    "cursor: -webkit-grabbing;",
  "}",
  
  
  ".blocklyDraggable:active {",
    
    "cursor: url(\"<<<PATH>>>/handclosed.cur\"), auto;",
    "cursor: grabbing;",
    "cursor: -webkit-grabbing;",
  "}",
  ".blocklyBlockDragSurface .blocklyDraggable {",
    
    "cursor: url(\"<<<PATH>>>/handclosed.cur\"), auto;",
    "cursor: grabbing;",
    "cursor: -webkit-grabbing;",
  "}",

  ".blocklyDragging.blocklyDraggingDelete {",
    "cursor: url(\"<<<PATH>>>/handdelete.cur\"), auto;",
  "}",

  ".blocklyToolboxDelete {",
    "cursor: url(\"<<<PATH>>>/handdelete.cur\"), auto;",
  "}",

  ".blocklyToolboxGrab {",
    "cursor: url(\"<<<PATH>>>/handclosed.cur\"), auto;",
    "cursor: grabbing;",
    "cursor: -webkit-grabbing;",
  "}",

  ".blocklyDragging>.blocklyPath,",
  ".blocklyDragging>.blocklyPathLight {",
    "fill-opacity: .8;",
    "stroke-opacity: .8;",
  "}",

  ".blocklyDragging>.blocklyPathDark {",
    "display: none;",
  "}",

  ".blocklyDisabled>.blocklyPath {",
    "fill-opacity: .5;",
    "stroke-opacity: .5;",
  "}",

  ".blocklyDisabled>.blocklyPathLight,",
  ".blocklyDisabled>.blocklyPathDark {",
    "display: none;",
  "}",

  ".blocklyInsertionMarker>.blocklyPath,",
  ".blocklyInsertionMarker>.blocklyPathLight,",
  ".blocklyInsertionMarker>.blocklyPathDark {",
    "fill-opacity: .2;",
    "stroke: none",
  "}",

  ".blocklyReplaceable .blocklyPath {",
    "fill-opacity: 0.5;",
  "}",

  ".blocklyReplaceable .blocklyPathLight,",
  ".blocklyReplaceable .blocklyPathDark {",
    "display: none;",
  "}",

  ".blocklyText {",
    "cursor: default;",
    "fill: #fff;",
    "font-family: sans-serif;",
    "font-size: 11pt;",
  "}",

  ".blocklyNonEditableText>text {",
    "pointer-events: none;",
  "}",

  ".blocklyNonEditableText>rect,",
  ".blocklyEditableText>rect {",
    "fill: #fff;",
    "fill-opacity: .6;",
  "}",

  ".blocklyNonEditableText>text,",
  ".blocklyEditableText>text {",
    "fill: #000;",
  "}",

  ".blocklyEditableText:hover>rect {",
    "stroke: #fff;",
    "stroke-width: 2;",
  "}",

  ".blocklyBubbleText {",
    "fill: #000;",
  "}",

  ".blocklyFlyout {",
    "position: absolute;",
    "z-index: 20;",
  "}",
  ".blocklyFlyoutButton {",
    "fill: #888;",
    "cursor: default;",
  "}",

  ".blocklyFlyoutButtonShadow {",
    "fill: #666;",
  "}",

  ".blocklyFlyoutButton:hover {",
    "fill: #aaa;",
  "}",

  ".blocklyFlyoutLabel {",
    "cursor: default;",
  "}",

  ".blocklyFlyoutLabelBackground {",
    "opacity: 0;",
  "}",

  ".blocklyFlyoutLabelText {",
    "fill: #000;",
  "}",


  ".blocklySvg text, .blocklyBlockDragSurface text {",
    "user-select: none;",
    "-moz-user-select: none;",
    "-ms-user-select: none;",
    "-webkit-user-select: none;",
    "cursor: inherit;",
  "}",

  ".blocklyHidden {",
    "display: none;",
  "}",

  ".blocklyFieldDropdown:not(.blocklyHidden) {",
    "display: block;",
  "}",

  ".blocklyIconGroup {",
    "cursor: default;",
  "}",

  ".blocklyIconGroup:not(:hover),",
  ".blocklyIconGroupReadonly {",
    "opacity: .6;",
  "}",

  ".blocklyIconShape {",
    "fill: #00f;",
    "stroke: #fff;",
    "stroke-width: 1px;",
  "}",

  ".blocklyIconSymbol {",
    "fill: #fff;",
  "}",

  ".blocklyMinimalBody {",
    "margin: 0;",
    "padding: 0;",
  "}",

  ".blocklyCommentForeignObject {",
    "position: relative;",
    "z-index: 0;",
  "}",

  ".blocklyCommentRect {",
    "fill: #E7DE8E;",
    "stroke: #bcA903;",
    "stroke-width: 1px",
  "}",

  ".blocklyCommentTarget {",
    "fill: transparent;",
    "stroke: #bcA903;",
  "}",

  ".blocklyCommentTargetFocused {",
    "fill: none;",
  "}",

  ".blocklyCommentHandleTarget {",
    "fill: none;",
  "}",

  ".blocklyCommentHandleTargetFocused {",
    "fill: transparent;",
  "}",

  ".blocklyFocused>.blocklyCommentRect {",
    "fill: #B9B272;",
    "stroke: #B9B272;",
  "}",

  ".blocklySelected>.blocklyCommentTarget {",
    "stroke: #fc3;",
    "stroke-width: 3px;",
  "}",


  ".blocklyCommentTextarea {",
    "background-color: #fef49c;",
    "border: 0;",
    "outline: 0;",
    "margin: 0;",
    "padding: 3px;",
    "resize: none;",
    "display: block;",
    "overflow: hidden;",
  "}",

  ".blocklyCommentDeleteIcon {",
    "cursor: pointer;",
    "fill: #000;",
    "display: none",
  "}",

  ".blocklySelected > .blocklyCommentDeleteIcon {",
    "display: block",
  "}",

  ".blocklyDeleteIconShape {",
    "fill: #000;",
    "stroke: #000;",
    "stroke-width: 1px;",
  "}",

  ".blocklyDeleteIconShape.blocklyDeleteIconHighlighted {",
    "stroke: #fc3;",
  "}",

  ".blocklyHtmlInput {",
    "border: none;",
    "border-radius: 4px;",
    "font-family: sans-serif;",
    "height: 100%;",
    "margin: 0;",
    "outline: none;",
    "padding: 0 1px;",
    "width: 100%",
  "}",

  ".blocklyMainBackground {",
    "stroke-width: 1;",
    "stroke: #c6c6c6;",  
  "}",

  ".blocklyMutatorBackground {",
    "fill: #fff;",
    "stroke: #ddd;",
    "stroke-width: 1;",
  "}",

  ".blocklyFlyoutBackground {",
    "fill: #ddd;",
    "fill-opacity: .8;",
  "}",

  ".blocklyTransparentBackground {",
    "opacity: 0;",
  "}",

  ".blocklyMainWorkspaceScrollbar {",
    "z-index: 20;",
  "}",

  ".blocklyFlyoutScrollbar {",
    "z-index: 30;",
  "}",

  ".blocklyScrollbarHorizontal, .blocklyScrollbarVertical {",
    "position: absolute;",
    "outline: none;",
  "}",

  ".blocklyScrollbarBackground {",
    "opacity: 0;",
  "}",

  ".blocklyScrollbarHandle {",
    "fill: #ccc;",
  "}",

  ".blocklyScrollbarBackground:hover+.blocklyScrollbarHandle,",
  ".blocklyScrollbarHandle:hover {",
    "fill: #bbb;",
  "}",

  ".blocklyZoom>image, .blocklyZoom>svg>image {",
    "opacity: .4;",
  "}",

  ".blocklyZoom>image:hover, .blocklyZoom>svg>image:hover {",
    "opacity: .6;",
  "}",

  ".blocklyZoom>image:active, .blocklyZoom>svg>image:active {",
    "opacity: .8;",
  "}",

  ".blocklyFlyout .blocklyScrollbarHandle {",
    "fill: #bbb;",
  "}",

  ".blocklyFlyout .blocklyScrollbarBackground:hover+.blocklyScrollbarHandle,",
  ".blocklyFlyout .blocklyScrollbarHandle:hover {",
    "fill: #aaa;",
  "}",

  ".blocklyInvalidInput {",
    "background: #faa;",
  "}",

  ".blocklyAngleCircle {",
    "stroke: #444;",
    "stroke-width: 1;",
    "fill: #ddd;",
    "fill-opacity: .8;",
  "}",

  ".blocklyAngleMarks {",
    "stroke: #444;",
    "stroke-width: 1;",
  "}",

  ".blocklyAngleGauge {",
    "fill: #f88;",
    "fill-opacity: .8;",
  "}",

  ".blocklyAngleLine {",
    "stroke: #f00;",
    "stroke-width: 2;",
    "stroke-linecap: round;",
    "pointer-events: none;",
  "}",

  ".blocklyContextMenu {",
    "border-radius: 4px;",
    "max-height: 100%;",
  "}",

  ".blocklyDropdownMenu {",
    "padding: 0 !important;",
  "}",

  
  ".blocklyWidgetDiv .goog-option-selected .goog-menuitem-checkbox,",
  ".blocklyWidgetDiv .goog-option-selected .goog-menuitem-icon {",
    "background: url(<<<PATH>>>/sprites.png) no-repeat -48px -16px !important;",
  "}",

  
  ".blocklyToolboxDiv {",
    "background-color: #ddd;",
    "overflow-x: visible;",
    "overflow-y: auto;",
    "position: absolute;",
    "user-select: none;",
    "-moz-user-select: none;",
    "-ms-user-select: none;",
    "-webkit-user-select: none;",
    "z-index: 70;", 
    "-webkit-tap-highlight-color: transparent;", 
  "}",

  ".blocklyTreeRoot {",
    "padding: 4px 0;",
  "}",

  ".blocklyTreeRoot:focus {",
    "outline: none;",
  "}",

  ".blocklyTreeRow {",
    "height: 22px;",
    "line-height: 22px;",
    "margin-bottom: 3px;",
    "padding-right: 8px;",
    "white-space: nowrap;",
  "}",

  ".blocklyHorizontalTree {",
    "float: left;",
    "margin: 1px 5px 8px 0;",
  "}",

  ".blocklyHorizontalTreeRtl {",
    "float: right;",
    "margin: 1px 0 8px 5px;",
  "}",

  ".blocklyToolboxDiv[dir=\"RTL\"] .blocklyTreeRow {",
    "margin-left: 8px;",
  "}",

  ".blocklyTreeRow:not(.blocklyTreeSelected):hover {",
    "background-color: #e4e4e4;",
  "}",

  ".blocklyTreeSeparator {",
    "border-bottom: solid #e5e5e5 1px;",
    "height: 0;",
    "margin: 5px 0;",
  "}",

  ".blocklyTreeSeparatorHorizontal {",
    "border-right: solid #e5e5e5 1px;",
    "width: 0;",
    "padding: 5px 0;",
    "margin: 0 5px;",
  "}",


  ".blocklyTreeIcon {",
    "background-image: url(<<<PATH>>>/sprites.png);",
    "height: 16px;",
    "vertical-align: middle;",
    "width: 16px;",
  "}",

  ".blocklyTreeIconClosedLtr {",
    "background-position: -32px -1px;",
  "}",

  ".blocklyTreeIconClosedRtl {",
    "background-position: 0 -1px;",
  "}",

  ".blocklyTreeIconOpen {",
    "background-position: -16px -1px;",
  "}",

  ".blocklyTreeSelected>.blocklyTreeIconClosedLtr {",
    "background-position: -32px -17px;",
  "}",

  ".blocklyTreeSelected>.blocklyTreeIconClosedRtl {",
    "background-position: 0 -17px;",
  "}",

  ".blocklyTreeSelected>.blocklyTreeIconOpen {",
    "background-position: -16px -17px;",
  "}",

  ".blocklyTreeIconNone,",
  ".blocklyTreeSelected>.blocklyTreeIconNone {",
    "background-position: -48px -1px;",
  "}",

  ".blocklyTreeLabel {",
    "cursor: default;",
    "font-family: sans-serif;",
    "font-size: 16px;",
    "padding: 0 3px;",
    "vertical-align: middle;",
  "}",

  ".blocklyToolboxDelete .blocklyTreeLabel {",
    "cursor: url(\"<<<PATH>>>/handdelete.cur\"), auto;",
  "}",

  ".blocklyTreeSelected .blocklyTreeLabel {",
    "color: #fff;",
  "}",

  
  ".blocklyColourTable {",
    "border-collapse: collapse;",
  "}",

  ".blocklyColourTable>tr>td {",
    "border: 1px solid #666;",
    "padding: 0;",
  "}",

  ".blocklyColourTable>tr>td>div {",
    "border: 1px solid #666;",
    "height: 13px;",
    "width: 15px;",
  "}",

  ".blocklyColourTable>tr>td>div:hover {",
    "border: 1px solid #fff;",
  "}",

  ".blocklyColourSelected, .blocklyColourSelected:hover {",
    "border: 1px solid #000 !important;",
  "}",


  ".blocklyWidgetDiv .goog-menu {",
    "background: #fff;",
    "border-color: #ccc #666 #666 #ccc;",
    "border-style: solid;",

    "border-width: 1px;",
    "cursor: default;",
    "font: normal 13px Arial, sans-serif;",
    "margin: 0;",
    "outline: none;",
    "padding: 4px 0;",
    "position: absolute;",
    "overflow-y: auto;",
    "overflow-x: hidden;",
    "max-height: 100%;",
    "z-index: 20000;",  
  "}",

  ".blocklyDropDownDiv .goog-menu {",
    "cursor: default;",
    "font: normal 13px \"Helvetica Neue\", Helvetica, sans-serif;",
    "outline: none;",
    "z-index: 20000;",  
  "}",

  ".blocklyWidgetDiv .goog-menuitem, ",
  ".blocklyDropDownDiv .goog-menuitem {",
    "color: #000;",
    "font: normal 13px Arial, sans-serif;",
    "list-style: none;",
    "margin: 0;",
     
    "min-width: 7em;",
    "padding: 5px 5px 5px 28px;",
    "white-space: nowrap;",
  "}",

  
  
  ".blocklyWidgetDiv .goog-menuitem.goog-menuitem-rtl, ",
  ".blocklyDropDownDiv .goog-menuitem.goog-menuitem-rtl {",
     
    "padding-left: 5px;",
    "padding-right: 28px;",
  "}",

  
  ".blocklyWidgetDiv .goog-menu-nocheckbox .goog-menuitem, ",
  ".blocklyWidgetDiv .goog-menu-noicon .goog-menuitem, ",
  ".blocklyDropDownDiv .goog-menu-nocheckbox .goog-menuitem, ",
  ".blocklyDropDownDiv .goog-menu-noicon .goog-menuitem { ",
    "padding-left: 12px;",
  "}",

  
  ".blocklyWidgetDiv .goog-menu-noaccel .goog-menuitem, ",
  ".blocklyDropDownDiv .goog-menu-noaccel .goog-menuitem {",
    "padding-right: 20px;",
  "}",

  ".blocklyWidgetDiv .goog-menuitem-content, ",
  ".blocklyDropDownDiv .goog-menuitem-content {",
    "color: #000;",
    "font: normal 13px Arial, sans-serif;",
  "}",

  
  ".blocklyWidgetDiv .goog-menuitem-disabled .goog-menuitem-accel, ",
  ".blocklyWidgetDiv .goog-menuitem-disabled .goog-menuitem-content, ",
  ".blocklyDropDownDiv .goog-menuitem-disabled .goog-menuitem-accel, ",
  ".blocklyDropDownDiv .goog-menuitem-disabled .goog-menuitem-content {",
    "color: #ccc !important;",
  "}",

  ".blocklyWidgetDiv .goog-menuitem-disabled .goog-menuitem-icon, ",
  ".blocklyDropDownDiv .goog-menuitem-disabled .goog-menuitem-icon {",
    "opacity: 0.3;",
    "filter: alpha(opacity=30);",
  "}",

  
  ".blocklyWidgetDiv .goog-menuitem-highlight, ",
  ".blocklyWidgetDiv .goog-menuitem-hover {",
    "background-color: #d6e9f8;",
     
    "border-color: #d6e9f8;",
    "border-style: dotted;",
    "border-width: 1px 0;",
    "padding-bottom: 4px;",
    "padding-top: 4px;",
  "}",

  ".blocklyDropDownDiv .goog-menuitem-highlight, ",
  ".blocklyDropDownDiv .goog-menuitem-hover {",
    "background-color: rgba(0, 0, 0, 0.2);",
  "}",

  
  ".blocklyWidgetDiv .goog-menuitem-checkbox, ",
  ".blocklyWidgetDiv .goog-menuitem-icon, ",
  ".blocklyDropDownDiv .goog-menuitem-checkbox, ",
  ".blocklyDropDownDiv .goog-menuitem-icon {",
    "background-repeat: no-repeat;",
    "height: 16px;",
    "left: 6px;",
    "position: absolute;",
    "right: auto;",
    "vertical-align: middle;",
    "width: 16px;",
  "}",

  
  
  ".blocklyWidgetDiv .goog-menuitem-rtl .goog-menuitem-checkbox, ",
  ".blocklyWidgetDiv .goog-menuitem-rtl .goog-menuitem-icon, ",
  ".blocklyDropDownDiv .goog-menuitem-rtl .goog-menuitem-checkbox, ",
  ".blocklyDropDownDiv .goog-menuitem-rtl .goog-menuitem-icon {",
     
    "left: auto;",
    "right: 6px;",
  "}",

  ".blocklyWidgetDiv .goog-option-selected .goog-menuitem-checkbox, ",
  ".blocklyWidgetDiv .goog-option-selected .goog-menuitem-icon, ",
  ".blocklyDropDownDiv .goog-option-selected .goog-menuitem-checkbox, ",
  ".blocklyDropDownDiv .goog-option-selected .goog-menuitem-icon {",
     
    "background: url(//ssl.gstatic.com/editor/editortoolbar.png) no-repeat -512px 0;",
    "position: static;", 
    "float: left;",
    "margin-left: -24px;",
  "}",

  ".blocklyWidgetDiv .goog-menuitem-rtl .goog-menuitem-checkbox, ",
  ".blocklyWidgetDiv .goog-menuitem-rtl .goog-menuitem-icon, ",
  ".blocklyDropDownDiv .goog-menuitem-rtl .goog-menuitem-checkbox, ",
  ".blocklyDropDownDiv .goog-menuitem-rtl .goog-menuitem-icon {",
    "float: right;",
    "margin-right: -24px;",
  "}",


  
  ".blocklyWidgetDiv .goog-menuitem-accel, ",
  ".blocklyDropDownDiv .goog-menuitem-accel {",
    "color: #999;",
     
     
    "direction: ltr;",
    "left: auto;",
    "padding: 0 6px;",
    "position: absolute;",
    "right: 0;",
    "text-align: right;",
  "}",

  
  
  ".blocklyWidgetDiv .goog-menuitem-rtl .goog-menuitem-accel, ",
  ".blocklyDropDownDiv .goog-menuitem-rtl .goog-menuitem-accel {",
     
    "left: 0;",
    "right: auto;",
    "text-align: left;",
  "}",

  
  ".blocklyWidgetDiv .goog-menuitem-mnemonic-hint, ",
  ".blocklyDropDownDiv .goog-menuitem-mnemonic-hint {",
    "text-decoration: underline;",
  "}",

  ".blocklyWidgetDiv .goog-menuitem-mnemonic-separator, ",
  ".blocklyDropDownDiv .goog-menuitem-mnemonic-separator {",
    "color: #999;",
    "font-size: 12px;",
    "padding-left: 4px;",
  "}",


  ".blocklyWidgetDiv .goog-menuseparator, ",
  ".blocklyDropDownDiv .goog-menuseparator {",
    "border-top: 1px solid #ccc;",
    "margin: 4px 0;",
    "padding: 0;",
  "}"])


;;; Source: https://gist.github.com/thomasdenney/aa76acb36d47120ee338b3bd96459556#file-blockly-png-js-L28
;;; Warning: on a page, these css bleed into each other. 

(def max-image-size {:width 800 :height 500})
(defn downscaled-size [canvas]
  (let [bbox (.getBBox canvas)
        width (.-width bbox)
        height (.-height bbox)
        scale (Math/min (/ (:height max-image-size) height)
                        (/ (:width max-image-size) width)
                        1)]
    {:width (* width scale)
     :height (* height scale)}
    ))

(defn workspace-image []
  (let [canvas (.-svgBlockCanvas_ (.-mainWorkspace js/Blockly))
        cp (.cloneNode canvas true)
        style-elt (.createElementNS js/document "http://www.w3.org/2000/svg" "style")]
    (.removeAttribute cp "width")
    (.removeAttribute cp "height")
    (.removeAttribute cp "transform")
    (set! (.-textContent style-elt)
          ;; the css is null for some reason
          (str/join "" blockly-css))  ; (.join (.-CONTENT js/Blockly.Css) "")

    (.insertBefore cp style-elt (.-firstChild cp))

    ;; Creates a complete SVG document with the correct bounds (it is necessary to get the viewbox right, in the case of negative offsets)
    (let [{:keys [width height]} (downscaled-size canvas)
          bbox (.getBBox canvas)
          xml  (.serializeToString (js/XMLSerializer.) cp)
          xml (str "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" width=\""
                   width
                   "\" height=\""
                   height
                   "\" viewBox=\""
                   (str/join " " [(.-x bbox) (.-y bbox) (.-width bbox) (.-height bbox)])
                   "\"><rect width=\"100%\" height=\"100%\" fill=\"white\"></rect>"
                   xml
                   "</svg>")
          ]
      xml)))

(defn now []
  (.now js/Date))

(rf/reg-event-db
 :save
 (fn [db _]
   (let [item
         {:blockdef (bo/workspace-xml-string) 
          :date-created (now)
          :image (workspace-image)
          }]
     (prn :item item)
     (api/ajax-post "/api/library/save"
                    {:params {:item item}
                     :handler #(rf/dispatch [:saved %])})

     db)))

(rf/reg-event-db
 :browse
 (fn [db _]
   (api/ajax-get "/api/library/list"
                 {:handler (fn [data]
                              (rf/dispatch [:library-data data]))}
                 )
   (assoc db :view :library)))

(rf/reg-event-db
 :library-data
 (fn [db [_ data]]
   (assoc db :library data)))

(rf/reg-sub
 :library
 (fn [db _]
   (get db :library)))

(defn render-item
  [item]
  [:div
   [:span (:uuid item)]])               ;TODO temp obv

(defn browse
  []
  [:div
   ;; TODO close box
   "Hello sailor 2"
   `[:ul
    ~@(map render-item @(rf/subscribe [:library]))]])
