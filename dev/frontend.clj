(ns frontend
  (:require [mount.core :as mount]
            [shadow.cljs.devtools.api :as cljs]
            [shadow.cljs.devtools.server :as shadow]
            [garden-gnome.watcher :as gnome]))


(mount/defstate shadow-cljs-server
  :start (shadow/start!)
  :stop (shadow/stop!))


(mount/defstate cljs-app-watcher
  :start (cljs/watch :app)
  :stop (cljs/stop-worker :app))


(mount/defstate css-watcher
  :start (gnome/start! (gnome/default-config))
  :stop (gnome/stop! css-watcher))
