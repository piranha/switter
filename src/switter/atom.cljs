(ns switter.atom)

(def app (js/require "app"))

(def BrowserWindow (js/require "browser-window"))
(def crash-reporter (js/require "crash-reporter"))

(def main-window (atom nil))

(defn init-browser []
  (let [browser (BrowserWindow. #js {:width 400 :height 600})]
    (.loadUrl browser "http://localhost:3000/index.html")
    (.on browser "closed" #(reset! main-window nil))
    (reset! main-window browser)))

(defn quit-app []
  (when-not (= js/process.platform "darwin")
    (.quit app)))

(.on app "window-all-closed" quit-app)
(.on app "ready" init-browser)

(defn init []
  (.start crash-reporter))

