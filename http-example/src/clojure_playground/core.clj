(ns clojure-playground.core
  (:require [clojure.data.json :as json])
  (:gen-class))

(def my-json-string "[{ \"key1\": \"value1\", \"key2\": \"value2\"}, { \"key1\": \"value3\", \"key2\": \"value4\" }]")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn json-to-map
  [json-string]
  (json/read-str json-string :key-fn keyword))

(defn my-first-transformation
  "This transformation will remove the second key of every object"
  [payload]
  (map (fn [value] (dissoc value :key2)) (json-to-map payload)))

(defn --
  [key input-map]
  (dissoc input-map key))


(defn my-transform-1
  "This is the equivalent to
  payload map -- key2"
  [payload]
  (map (fn [value] (-- :key2 value)) (json-to-map payload)))  

(defn my-transform-2
  [payload]
  (map (fn [value] (hash-map :new-key1 (get value :key1) :new-key2 (get value :key2))) (json-to-map payload)))
