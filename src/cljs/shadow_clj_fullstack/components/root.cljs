(ns shadow-clj-fullstack.components.root
  (:require [shadow-clj-fullstack.components.navigation :as nav]))


(defn root []
  [:div#root
   [nav/Navigation]
   [nav/Page]])
