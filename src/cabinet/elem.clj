(ns cabinet.elem
  (:use slingshot.slingshot)
  (:refer-clojure :exclude (list get delete)))

(def elems (atom {}))

(defn list []
  @elems)

(defn get [id]
  (or (@elems id)
      (throw :type :not-found
             :message (format "elem '%s' not found" id))))

(defn put [id attrs]
  (if (empty? attrs)
    (throw :type :invalid
           :message "attrs are empty")
    (let [new-attrs (merge (@elems id) attrs)]
      (swap! elems assoc id new-attrs)
      new-attrs)))

(defn delete [id]

 (let [old-attrs (get id)]
    (swap! elems dissoc id)
    old-attrs))
