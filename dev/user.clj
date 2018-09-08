(ns user
  (:require [clojure.tools.namespace.repl :as ns-tools]
            [clojure.pprint :refer [pprint]]
            [clojure.java.io :as io]
            [clojure.string :as str]

            [mount.core :as mount]
            [wrench.core :as cfg]
            [taoensso.timbre :as log]

            [logging]
            [frontend]
            [backend]))

(ns-tools/set-refresh-dirs "src" "dev")

;; Reloaded Workflow

; Start components won't be reloaded on (reset)
(def start-components [#'frontend/shadow-cljs-server
                       #'frontend/cljs-app-watcher
                       #'frontend/css-watcher])
; Reset components are stopped and started between code reloading
(def reset-components [#'backend/dev-http-server])


(defn start []
  (cfg/reset! :env (cfg/from-file "dev/config.edn"))
  (if-not (cfg/validate-and-print)
    (log/error "Unable to start, because config is not correct")
    (do (mount/start start-components)
        (mount/start reset-components)))
  :ready)


(defn stop []
  (mount/stop reset-components))


(defn reset []
  (stop)
  (ns-tools/refresh :after 'user/start))
