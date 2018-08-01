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

(defn- set-log-level [level]
  (.. (LoggerFactory/getLogger org.slf4j.Logger/ROOT_LOGGER_NAME)
      (setLevel (Level/valueOf (.toUpperCase (name level))))))

(defn- prepare-csv [{:keys [id resourceType] :as json-obj} txid]
  (let [json-str (json/generate-string (dissoc json-obj :id :resourceType))
        escaped-json (-> json-str
                         (str/replace "\\" "\\\\")
                         (str/replace "," "\\,"))]
    (str/join "," [id txid resourceType escaped-json])))

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

