(ns cabinet.elem
  (:use [hassium.core :exclude (delete)])
  (:use clojure.contrib.condition)
  (:refer-clojure :exclude (list get delete)))

(def ^{:private true} db
  (database "cabinet"))

(def elems (collection db "elems"))

(defn list []
  @(find-all elems))

(defn get [id]
  (or (find-one elems {:elem-id id})
      (raise :type :not-found
             :message (format "elem '%s' not found" id))))

(defn put [id attrs]
  (if (empty? attrs)
    (raise :type :invalid
           :message "attrs are empty")
    (let [new-attrs (merge (find-one elems {:elem-id id})
                           attrs
                           {:elem-id id})]
      (save elems new-attrs))))

(defn delete [id]
  (let [old-attrs (get id)]
    (hassium.core/delete elems {:elem-id id})
    old-attrs))
