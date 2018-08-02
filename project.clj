(defproject aidboxconv "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src"
                 "sansara/proto/src"
                 "sansara/fhir/src"
                 "sansara/fhir/import"]

  :resource-paths ["resources" "sansara/fhir/resources" "sansara/proto/resources"]

  :dependencies [[ch.qos.logback/logback-classic "1.2.2"]
                 [com.cognitect/transit-clj "0.8.300"]
                 [org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.postgresql/postgresql "9.4.1211.jre7"]
                 [org.clojure/data.csv "0.1.4"]
                 [pandect "0.6.1"]
                 [cheshire "5.6.3"]
                 [clj-pg "0.0.3"]
                 [clj-http "3.6.1"]
                 [clj-yaml "0.4.0"]
                 [crypto-password "0.2.0"]
                 [http-kit "2.2.0"]
                 [json-schema "0.2.0-RC3"]
                 [matcho "0.1.0-RC6"]
                 [garden "1.3.2"]
                 [hiccup "1.0.5"]
                 [clj-time "0.13.0"]
                 [clj-jwt "0.1.1" :exclusions [joda-time]]
                 [ring "1.5.1"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-codec "1.0.1"]
                 [route-map "0.0.4"]
                 [markdown-clj "1.0.2"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [io.minio/minio "3.0.11"]]
  :uberjar-name "aidboxconv.jar"
  :main aidboxconv.core
  :profiles {:uberjar {:aot :all :omit-source true}})
