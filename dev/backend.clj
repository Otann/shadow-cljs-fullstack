(ns backend
  (:require [io.pedestal.http :as http]
            [mount.core :as mount]
            [shadow-clj-fullstack.http-server :as http-server]))


(defn create-dev-http-server []
  (-> http-server/service
      http/default-interceptors
      http/dev-interceptors
      http/create-server))


(mount/defstate dev-http-server
  :start (http/start (create-dev-http-server))
  :stop (http/stop dev-http-server))
