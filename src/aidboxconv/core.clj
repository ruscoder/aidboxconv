(ns aidboxconv.core
  (:gen-class)
  (:require [cheshire.core :as json]
            [fhir.conv :as conv]
            [fhir.core]
            [clojure.tools.logging :as log]
            [proto.manifest :as manifest]
            [clojure.string :as str])
  (:import [java.io BufferedReader]
           [org.slf4j LoggerFactory]
           [ch.qos.logback.classic Logger Level]))

;; \0x2 is used as delimiter
(def delimiter (char 2))

(defn- set-log-level [level]
  (.. (LoggerFactory/getLogger org.slf4j.Logger/ROOT_LOGGER_NAME)
      (setLevel (Level/valueOf (.toUpperCase (name level))))))

(defn- prepare-csv [{:keys [id resourceType] :as res} txid]
  (let [json-str (json/generate-string (dissoc res :id :resourceType))]
    (str/join delimiter [id txid resourceType json-str])))

(defn -main [& args]
  (set-log-level :error)
  (let [fhir-version (keyword (or (second args) "fhir-3.0.1"))
        txid (first args)
        tr-data (conv/gen-import-transform (manifest/manifest {:import {fhir-version {}}}))]
    (if txid
      (doseq [line (line-seq (BufferedReader. *in*))]
        (println (-> line
                     (json/parse-string true)
                     (->> (conv/*import tr-data))
                     (prepare-csv txid))))
      (log/error "TXID required as the first argument"))))

(comment
 )

