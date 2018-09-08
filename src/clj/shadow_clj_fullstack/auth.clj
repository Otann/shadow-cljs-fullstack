(ns shadow-clj-fullstack.auth
  (:require [wrench.core :as cfg]
            [geheimtur.interceptor :as security]
            [geheimtur.util.auth :as auth]
            [ring.util.response :as r]
            [ring.util.response :as ring-resp]))


(cfg/def base-url {:require true, :doc "Base URL of the deployed app"})
(cfg/def github-client-id {:require true, :doc "Public part of OAuth config"})
(cfg/def github-client-secret {:require true, :doc "Private part of OAuth config", :secret true})


(def flow (security/interactive {:login-uri "/login"}))


(def require-user
  (security/guard :roles #{:user}
                  :silent? false))


(defn logout [_request]
  (auth/logout (r/redirect "/login")))


(defn on-github-success
  [_ {:keys [identity return]}]
  (let [user {:name      (:login identity)
              :roles     #{:user}
              :full-name (:name identity)}]
    (-> (ring-resp/redirect return)
        (auth/authenticate user))))


(defn providers []
  {:github {:client-id          github-client-id
            :client-secret      github-client-secret
            :scope              "user:email"
            :auth-url           "https://github.com/login/oauth/authorize"
            :token-url          "https://github.com/login/oauth/access_token"
            :user-info-url      "https://api.github.com/user"
            :callback-uri       (str base-url "/token")
            :on-success-handler on-github-success}})
