(ns cabinet.web
  (:use compojure.core)
  (:use ring.middleware.json-params)
  (:require [clj-json.core :as json])
  (:require [cabinet.elem :as elem]))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defroutes handler
  (GET "/" []
       (json-response {"hello" "world"}))
  (PUT "/" [name]
       (json-response {"hello" name}))
  (context "/elems" []
           (GET "/" []
                (json-response (elem/list)))
           (GET "/:id" [id]
                (json-response (elem/get id)))
           (PUT "/:id" [id attrs]
                (json-response (elem/put id attrs)))
           (DELETE "/:id" [id]
                   (json-response (elem/delete id)))))

(def app
  (-> handler
      wrap-json-params))
