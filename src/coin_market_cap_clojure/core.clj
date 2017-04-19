(ns coin-market-cap-clojure.core
  (:require [coin-market-cap-clojure.request :refer [fetch-data]])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (fetch-data "https://poloniex.com/public?command=returnTicker")
  (println "=======================")
  (fetch-data "https://blockchain.info/ticker?cors=true"))
