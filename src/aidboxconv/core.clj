(ns aidboxconv.core
  (:gen-class)
  (:require [cheshire.core :as json]
            [fhir.conv :as conv])
  (:import (java.io BufferedReader)))

(def tr-data (clojure.core/read-string (slurp "tr-data")))
(defn -main [& args]
  (doseq [line (line-seq (BufferedReader. *in*))]
    (println (-> line
                 (json/parse-string true)
                 (->> (conv/*import tr-data))
                 (json/generate-string)))))


(comment
 (def tr-data (conv/gen-import-transform (proto.manifest/manifest {:import {:fhir-3.0.1 {}}})))
 ( tr-data)
 )