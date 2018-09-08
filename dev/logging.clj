(ns logging
  (:require [clojure.string :as str]
            [taoensso.timbre :as timbre]))


(defn logging-fn [{:keys [level ?err msg_ ?ns-str ?file _hostname timestamp ?line]}]
  (str (force timestamp) " "
       (str/upper-case (name level)) " "
       "[" (or ?ns-str ?file "?") ":" (or ?line "?") "] - "
       (force msg_)
       (when-let [err ?err]
         (str "\n" (timbre/stacktrace err)))))


(timbre/merge-config!
  {:level          :debug
   :ns-blacklist   ["org.eclipse.jetty*"
                    "io.pedestal*"
                    "org.apache.http*"]
   :timestamp-opts {:pattern "HH:mm:ss"}
   :output-fn      logging-fn})
