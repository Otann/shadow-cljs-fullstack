(defproject shadow-clj-fullstack "0.1.0-SNAPSHOT"
  :description "Example of full-stack Clojure/Script project"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot shadow-clj-fullstack.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
