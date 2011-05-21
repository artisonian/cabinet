(defproject cabinet "0.0.2"
  :description "REST datastore interface."
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [ring-json-params "0.1.3"]
                 [compojure "0.6.3"]
                 [clj-json "0.3.2"]
                 [hassium "0.2.1"]]
  :dev-dependencies [[lein-ring "0.4.0"]]
  :ring {:handler cabinet.web/app})
