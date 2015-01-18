#!/usr/bin/env boot

(set-env!
  :source-paths #{"src" "style"}
  :resource-paths #{"resources/public"}
  :dependencies '[[adzerk/boot-cljs "0.0-2629-8" :scope "test"]
                  [adzerk/boot-cljs-repl "0.1.7" :scope "test"]
                  [adzerk/boot-reload "0.2.3" :scope "test"]
                  [pandeiro/boot-http "0.5.0" :scope "test"]
                  [jeluard/boot-notify "0.1.1" :scope "test"]

                  [org.webjars/bootstrap "3.3.1"]
                  [deraen/boot-less "0.2.1" :scope "test"]

                  [cljsjs/react "0.12.2-3"]
                  [cljsjs/boot-cljsjs "0.4.0" :scope "test"]

                  [rum "0.2.0"]
                  [datascript "0.7.2"]
                  [keybind "1.0.0"]])

(task-options! pom
  {:project 'switter
   :version "0.1.0-SNAPSHOT"})

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[pandeiro.boot-http :refer [serve]]
  '[cljsjs.boot-cljsjs :refer [from-cljsjs]]
  '[deraen.boot-less :refer [less]]
  '[jeluard.boot-notify :refer [notify]])

(deftask dev
  "Start dev compiler/watcher/server"
  []
  (comp
    (serve :port 3000)
    (from-cljsjs :profile :development)
    (watch)
    ;;(speak)
    (notify)
    (reload :on-jsload 'switter.ui/trigger-render)
    (cljs-repl)
    (cljs :source-map true
          :optimizations :none
          :compiler-options {:cache-analysis true})
    (less :source-map true)))
