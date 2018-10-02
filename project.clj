(defproject shadow-clj-fullstack "0.1.0-SNAPSHOT"
  :description "Example of full-stack Clojure/Script project"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.474"]
                 [thheller/shadow-cljs "2.6.3"]

                 ;; Logging
                 [com.taoensso/timbre "4.10.0"]
                 [com.fzakaria/slf4j-timbre "0.3.12"]

                 ;; Backend
                 [ring/ring-core "1.6.2"]
                 [io.pedestal/pedestal.service "0.5.4"]
                 [io.pedestal/pedestal.jetty "0.5.4"]
                 [geheimtur "0.3.5"]
                 [hiccup "1.0.5"]
                 [garden "1.3.3"]
                 [mount "0.1.11"]
                 [wrench "0.3.0"]

                 ;; Frontend
                 [reagent "0.8.1"]
                 [re-frame "0.10.5"]
                 [day8.re-frame/http-fx "0.1.6"]
                 [bidi "2.1.3"]
                 [kibu/pushy "0.3.8"]]

  :min-lein-version "2.6.1"

  :source-paths ["src/clj" "src/cljs" "src/cljc" "src/css"]
  :test-paths ["test/clj" "test/cljc"]
  :clean-targets ^{:protect false} [:target-path :compile-path "resources/public/js" "node_modules"]

  :uberjar-name "shadow-clj-fullstack.jar"
  :main shadow-clj-fullstack.main

  :aliases {"js-watch" ["run" "-m" "shadow.cljs.devtools.cli" "watch" "app"]
            "js-build" ["run" "-m" "shadow.cljs.devtools.cli" "release" "app"]}

  :profiles {:dev     {:repl-options {:init-ns user}
                       :source-paths ["dev"]
                       :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                      [garden-gnome "0.1.0"]]}

             :uberjar {:aot          :all
                       :omit-source  true
                       :dependencies [[garden-gnome "0.1.0"]]
                       :prep-tasks   ["compile" "js-build" "css-build"]}})
