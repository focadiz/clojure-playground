(ns clojure-playground.core
  (:require [clojure.data.json :as json])
  (:require [clj-http.client :as client])
  (:gen-class))

(def url "https://api.thedogapi.com/v1/breeds")
(def file "/home/felipe/Downloads/test.json")

(defn string-to-map
  [json-string]
  (json/read-str json-string :key-fn keyword))

(defn map-to-json
  [my-map]
  (json/write-str my-map))

(defn transform
  [payload]
  (pmap (fn [value] (hash-map :breed (get value :name))) payload))

(defn get-json
  []
  (get (client/get url) :body))

(defn get-breeds
  []
  (slurp url))

(defn write-to-file
  [my-string]
  (spit file my-string))
         

(defn http-application
  "This function consumes a REST endpoint, parses the response, transforms it and generates a different JSON"
  []
  (write-to-file (map-to-json (transform (string-to-map (get-breeds))))))
