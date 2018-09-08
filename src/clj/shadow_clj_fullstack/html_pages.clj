(ns shadow-clj-fullstack.html-pages
  (:require [hiccup.page :as h]
            [ring.util.response :as r]))


(defn page [meta-info & body]
  (r/response
    (h/html5 {:lang "en"}
      [:head
       [:title (get meta-info :title "Shadow Full Stack")]
       [:meta {:charset "UTF-8"}]
       (h/include-css "https://unpkg.com/normalize.css@7.0.0")
       (h/include-css "https://unpkg.com/@blueprintjs/core@3.3.0/lib/css/blueprint.css")
       (h/include-css "https://unpkg.com/@blueprintjs/icons@3.1.0/lib/css/blueprint-icons.css")
       [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
       [:link {:rel "stylesheet" :href "/css/screen.css"}]]
      (into [:body] body))))


(defn home [_]
  (r/redirect "/app"))


(defn spa [_]
  (page {:title "Shadow Full Stack - App"}
    [:div#app]
    (h/include-js "/js/index.js")))


(defn login [_]
  (page {:title "Shadow Full Stack - Login"}
    [:div#login
     [:h1 "Shadow Full Stack App"]
     [:a.bp3-button.bp3-intent-success.bp3-large
      {:href "/oauth?provider=github"}
      "Login via GitHub"]]))
