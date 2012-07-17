(ns cabinet.web
  (:use compojure.core)
  (:use ring.middleware.json-params)
  (:require [clj-json.core :as json])
  (:require [cabinet.elem :as elem])
  (:require slingshot.slingshot)
  (:import org.codehaus.jackson.JsonParseException)
)

(def error-codes
  {:invalid 400
   :not-found 404})

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(defn wrap-error-handling [handler]
  (fn [req]
    (try
      (or (handler req)
          (json-response {"error" "resource not found"} 404))
      (catch JsonParseException e
        (json-response {"error" "malformed json"} 400))
      (catch Exception e
        (let [{:keys [type message]} (meta e)]
          (json-response {"error" message} (error-codes type)))))))

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
      wrap-json-params
      wrap-error-handling))
