(ns shadow-clj-fullstack.state.user-info
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax]))


(def initial-db
  {::user    nil
   ::loading true})


(def initial-http
  {:method          :get
   :uri             "/api/user"
   :format          (ajax/json-request-format)
   :response-format (ajax/json-response-format {:keywords? true})
   :on-success      [::success]
   :on-failure      [::failure]})


(rf/reg-event-db ::success
  (fn [db [_ result]]
    (into db {::user    (:user result)
              ::loading false})))


(rf/reg-event-db ::failure
  (fn [db [_ _result]]
    (into db {::user    nil
              ::loading false})))


(rf/reg-sub ::info
  (fn [db _]
    (::user db)))
