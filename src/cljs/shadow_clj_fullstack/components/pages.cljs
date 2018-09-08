(ns shadow-clj-fullstack.components.pages
  (:require ["@blueprintjs/core" :as bp]
            [reagent.core :as r]))


(defn Landing []
  [:> bp/NonIdealState
   {:icon        "endorsed"
    :title       "Hello world!"
    :description (r/as-element
                   [:div
                    [:p "This is landing page for your frontend app."]
                    [:p "Use top-left menu for navigating to another screen."]])}])


(defn Feature []
  [:> bp/NonIdealState
   {:icon        "search"
    :title       "Feature Page"
    :description "Your feature with dedicated navigation URL"}])
