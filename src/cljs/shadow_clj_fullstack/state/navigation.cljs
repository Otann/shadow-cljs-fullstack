(ns shadow-clj-fullstack.state.navigation
  (:require [re-frame.core :as rf]))


(rf/reg-event-db ::set-active-page
  (fn [db [_ page-id]]
    (assoc db ::active-page page-id)))


(rf/reg-sub ::active-page
  (fn [db _]
    (::active-page db)))
