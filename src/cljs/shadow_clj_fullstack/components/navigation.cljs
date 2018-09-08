(ns shadow-clj-fullstack.components.navigation
  (:require ["@blueprintjs/core" :as bp]
            [shadow-clj-fullstack.components.pages :as pages]
            [shadow-clj-fullstack.state.navigation :as nav]
            [shadow-clj-fullstack.state.user-info :as user]
            [shadow-clj-fullstack.routes :as routes]
            [re-frame.core :as rf]))


(defn TopMenu []
  [:> bp/Popover {:position "bottom-left" :interactionKind "click" :autoFocus false}
   [:> bp/Button {:icon "menu" :text "Menu" :minimal true}]
   [:> bp/Menu {}
    [:> bp/MenuItem {:text "Landing" :onClick #(routes/navigate! ::routes/landing)}]
    [:> bp/MenuItem {:text "Feature" :onClick #(routes/navigate! ::routes/feature)}]
    [:> bp/MenuItem {:text "Test"}
     [:> bp/MenuItem {:text "(println)" :onClick #(println "printed")}]
     [:> bp/MenuItem {:text "(js/alert)" :onClick #(js/alert "Alerted")}]]]])


(defn Navigation []
  [:> bp/Navbar {:className "vg-navbar" :fixedToTop true}
   [:> bp/NavbarGroup {:align "left"}
    [TopMenu]]
   [:> bp/NavbarGroup {:align "right"}
    [:> bp/Button {:icon "user" :text (:name @(rf/subscribe [::user/info])) :minimal true}]]])


(defn Page []
  [:div.content
   (case @(rf/subscribe [::nav/active-page])
     ::routes/landing [pages/Landing]
     ::routes/feature [pages/Feature]
     [:> bp/NonIdealState
      {:icon        "search"
       :title       "Page Not Found"
       :description "Go back and choose something else"}])])
