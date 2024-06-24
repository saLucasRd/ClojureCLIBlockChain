(ns blockchain-api.blockchain
  (:require [clojure.data.json :as json]
            [clojure.string :as str])
  (:import (java.security MessageDigest)
           (java.nio.charset StandardCharsets)))

(defn sha-256 [s]
  (let [digest (MessageDigest/getInstance "SHA-256")]
    (.update digest (.getBytes s StandardCharsets/UTF_8))
    (let [hash (.digest digest)]
      (apply str (map (partial format "%02x") hash)))))

(def genesis-block
  {:index 0
   :nonce 0
   :transactions []
   :previous-hash "0"
   :hash ""})

(defn calculate-hash [block]
  (sha-256 (str (:index block) (:nonce block) (json/write-str (:transactions block)) (:previous-hash block))))

(def blockchain (atom [(assoc genesis-block :hash (calculate-hash genesis-block))]))

(defn create-transaction [from to amount]
  {:from from
   :to to
   :amount amount})

(defn get-latest-block []
  (last @blockchain))

(defn proof-of-work [block difficulty]
  (loop [nonce 0]
    (let [new-block (assoc block :nonce nonce :hash (calculate-hash (assoc block :nonce nonce)))]
      (if (str/starts-with? (:hash new-block) (apply str (repeat difficulty "0")))
        new-block
        (recur (inc nonce))))))

(defn add-block [transactions]
  (let [previous-block (get-latest-block)
        new-block {:index (inc (:index previous-block))
                   :nonce 0
                   :transactions transactions
                   :previous-hash (:hash previous-block)
                   :hash ""}]
    (let [mined-block (proof-of-work new-block 4)]
      (swap! blockchain conj mined-block)
      mined-block)))

(defn get-blockchain []
  @blockchain)