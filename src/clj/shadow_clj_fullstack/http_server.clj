(ns shadow-clj-fullstack.http-server
  (:require [mount.core :as mount]
            [wrench.core :as cfg]

            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [ring.middleware.session.cookie :as cookie]
            [geheimtur.impl.oauth2 :as oauth]

            [shadow-clj-fullstack.auth :as auth]
            [shadow-clj-fullstack.html-pages :as pages]
            [shadow-clj-fullstack.http-api :as api]))


(cfg/def port {:name "HTTP_PORT" :spec int? :default 8080})


(def routes
  {"/" {:interceptors [auth/flow]
        :get          `pages/home

        "/health"     {:get `api/health}

        "/app"        {:interceptors [auth/require-user http/html-body]
                       ""            {:get [:spa-root `pages/spa]}
                       "/*"          {:get [:spa-rest `pages/spa]}}

        "/login"      {:interceptors [http/html-body] :get `pages/login}
        "/logout"     {:get `auth/logout}
        "/oauth"      {:get (oauth/authenticate-handler auth/providers)}
        "/token"      {:get (oauth/callback-handler auth/providers)}

        "/api"        {:interceptors [http/json-body auth/require-user]

                       "/echo"       {:get `api/echo}
                       "/user"       {:get `api/user}}}})


(def service
  {;; do not block thread that starts web server
   ::http/join?             false
   ::http/port              port
   ::http/host              "0.0.0.0"
   ::http/resource-path     "/public"
   ::http/routes            (route/expand-routes routes)
   ::http/enable-session    {:cookie-name "SID"
                             :store       (cookie/cookie-store)}

   ::http/type              :jetty
   ;; for dev only?
   ::http/secure-headers    {:content-security-policy-settings {:object-src "none"}}
   ::http/container-options {:h2c? true
                             :h2?  false
                             :ssl? false}})


(mount/defstate state
  :start (-> service
             http/default-interceptors
             http/create-server
             http/start)
  :stop (http/stop state))
