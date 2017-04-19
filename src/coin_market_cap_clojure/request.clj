(ns coin-market-cap-clojure.request
  (:require [clj-http.client :as client]))

(defn fetch-data
  "Gets data from url"
  [url-address]
  (let [data (client/get url-address
                         {:accept :json})
        body (:body data)]
    body))