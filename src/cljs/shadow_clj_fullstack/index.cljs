(ns shadow-clj-fullstack.index
  (:require [shadow.dom :as dom]
            [reagent.core :as r]
            [re-frame.core :as rf]
            [shadow-clj-fullstack.routes :as routes]
            [shadow-clj-fullstack.components.root :refer [root]]
            [shadow-clj-fullstack.state.global]))


;; initialise re-frame by broadcasting event
(rf/dispatch-sync [:initialize])


(defn render []
  (r/render [root] (dom/by-id "app")))


(defn reload! []
  (println "(reload!)")
  (routes/start!)
  (render))


(defn main! []
  (println "(main!)")
  (reload!))


(main!)

