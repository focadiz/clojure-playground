(ns http
  (:require [clojure.data.json :as json]))

(defn simple-request
  "Returns a string with a GET HTTP Request using the slurp function"
  [url]
  (json/read-str (slurp url)))
