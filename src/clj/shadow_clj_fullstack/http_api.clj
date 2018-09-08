(ns shadow-clj-fullstack.http-api
  (:require [geheimtur.util.auth :as auth]
            [taoensso.timbre :as log]))


(defn health [_request]
  {:status 200 :body "ok"})


(defn echo [request]
  {:status 200
   :body   (select-keys request [:query-params :headers])})


(defn user [request]
  {:status 200
   :body   {:user (auth/get-identity request)}})
