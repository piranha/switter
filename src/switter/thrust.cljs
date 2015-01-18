(ns switter.thrust)

(def Thrust (js/require "node-thrust"))
(def main-window (atom nil))

(defn -init [err api]
  (let [window (.window api #js {:root_url "http://localhost:3000/"
                                 :size #js {:width 400
                                            :height 800}})]
    (.on window "closed" #(.exit js/process 0))
    (doto window
      .show
      .focus)
    (swap! main-window window)))

(defn init []
  (Thrust -init))

