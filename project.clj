(defproject cabinet "0.0.2"
  :description "REST datastore interface."
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring-json-params "0.1.3"]
                 [compojure "1.1.1"]
                 [slingshot "0.10.3"]
                 [clj-json "0.3.2"]]

  :dev-dependencies [[lein-ring "0.7.1"]]
  :ring {:handler cabinet.web/app})

