(ns shadow-clj-fullstack.styles
  (:require [garden.def :refer [defstyles]]
            [garden.color :refer [rgba]]))


(def login
  [:div#login {:width           "100%"
               :height          "100%"
               :display         "flex"
               :flex-direction  "column"
               :align-items     "center"
               :justify-content "center"}
   [:h1 {:font-size "5rem"}]
   [:h2 {:font-size "2rem"}]])


(defstyles screen
  [:html {:height "100%"}]
  [:body {:height      "100%"
          :padding-top "50px"}]
  [:div#app {:height "100%"}]
  [:div#root {:height          "100%"
              :display         "flex"
              :flex-direction  "column"
              :align-items     "center"
              :justify-content "center"}]

  login)