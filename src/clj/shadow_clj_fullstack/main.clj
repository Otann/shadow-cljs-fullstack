(ns shadow-clj-fullstack.main
  "JAR entrypoint"
  (:gen-class)
  (:require [clojure.core.async :as a]
            [mount.core :as mount]
            [taoensso.timbre :as log]
            [wrench.core :as cfg]
            [shadow-clj-fullstack.http-server]))


(defonce running (a/chan))


(defn -main [& _args]
  (log/set-level! :info)
  (log/info "Starting application")

  (if-not (cfg/validate-and-print)
    (System/exit 1))

  (mount/start)

  (a/<!! running))
