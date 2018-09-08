(ns shadow-clj-fullstack.routes
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]
            [shadow-clj-fullstack.state.navigation :as navigation]))


(def routes ["/app" {""         ::landing
                     "/feature" ::feature}])


(defn- parse-url [url]
  (bidi/match-route routes url))


(defn- dispatch-route [matched-route]
  ;; dispatch-sync helps to render page immediately, without hitting 404, because it was not set
  (rf/dispatch-sync [::navigation/set-active-page (:handler matched-route)]))


(defonce ^:private history
  (pushy/pushy dispatch-route parse-url))


(defn start! []
  (pushy/start! history))


(defn url-for
  "Generates an URL for given page"
  [page-id & params]
  (apply bidi/path-for routes page-id params))


(defn navigate!
  "Changes URL in browser, which triggers also a dispatch event"
  [page-id & params]
  (pushy/set-token! history (apply url-for page-id params)))
