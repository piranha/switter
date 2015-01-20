(ns switter.ui
  (:require [rum :include-macros true]
            [datascript :as db]))

(rum/defc label [n text]
  [:div
   (for [i (range n)]
     [:div {:key i} (str text " ") [:span.badge i]])])

(defn trigger-render []
  (rum/mount (label 5 "abcd") (.getElementById js/document "content")))
