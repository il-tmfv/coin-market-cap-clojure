(ns coin-market-cap-clojure.core
  (:require [coin-market-cap-clojure.request :refer [fetch-data]])
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]])
  (:require [clojure.data.json :as json])
  (:gen-class))

(def store (agent {}))

(defn fetch-all []
  (let [polo-chan (thread (fetch-data "https://poloniex.com/public?command=returnTicker"))
        block-chan (thread (fetch-data "https://blockchain.info/ticker?cors=true"))
        xem-btc-price (get-in (json/read-str (<!! polo-chan)) ["BTC_XEM" "last"])
        btc-usd-price (get-in (json/read-str (<!! block-chan)) ["USD" "last"])
        xem-usd-price (* (bigdec xem-btc-price) (bigdec btc-usd-price))]
    xem-usd-price))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (fetch-all))
  (println "======================="))
