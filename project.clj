(defproject cabinet "0.0.1"
  :description "REST datastore interface."
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-jetty-adapter "0.3.7"]
                 [ring-json-params "0.1.3"]
                 [compojure "0.6.1"]
                 [clj-json "0.3.1"]]
  :dev-dependencies [[lein-ring "0.3.2"]]
  :ring {:handler cabinet.web/app})
