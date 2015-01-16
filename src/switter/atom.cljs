(ns switter.atom)

(declare init-browser quit-app)
(def app (js/require "app"))
(.on app "window-all-closed" quit-app)
(.on app "ready" init-browser)

(def BrowserWindow (js/require "browser-window"))
(def crash-reporter (js/require "crash-reporter"))

(def main-window (atom nil))

(defn init-browser []
  (let [browser (BrowserWindow. #js {:width 400 :height 600})]
    (reset! main-window browser)
    (.loadUrl browser (str "http://localhost:3000/"))
    (.on browser "closed" #(reset! main-window nil))))

(defn quit-app []
  (when-not (= js/process.platform "darwin")
    (.quit app)))

(defn init []
  (.start crash-reporter))

