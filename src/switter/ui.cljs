(ns switter.ui
  (:require [rum :include-macros true]
            [datascript :as db]))

(rum/defc label [n text]
  [:.label (repeat n text)])

(defn trigger-render []
  (rum/mount (label 5 "abc") (.-body js/document)))
