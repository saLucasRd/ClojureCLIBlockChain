(ns blockchain-cli.core
  (:require [clojure.string :as str]
            [blockchain-api.blockchain :as blockchain]))

(defn print-blockchain []
  (println "Blockchain:")
  (doseq [block (blockchain/get-blockchain)]
    (println (str/join "\n"
                       ["Index: " (:index block)
                        "Nonce: " (:nonce block)
                        "Transactions: " (pr-str (:transactions block))
                        "Previous Hash: " (:previous-hash block)
                        "Hash: " (:hash block)])
             (println "-------------------------"))))

(defn create-transaction [from to amount]
  (println "Creating transaction...")
  (let [transaction (blockchain/create-transaction from to amount)]
    (println "Transaction created:")
    (println (str/join "\n"
                       ["From: " from
                        "To: " to
                        "Amount: " amount]))))

(defn add-transaction []
  (println "Enter sender: ")
  (let [sender (read-line)]
    (println "Enter recipient: ")
    (let [recipient (read-line)]
      (println "Enter amount: ")
      (let [amount (Integer/parseInt (read-line))]
        (blockchain/add-block [(blockchain/create-transaction sender recipient amount)])
        (println "Transaction added.")
        (println "Blockchain updated: ")
        (print-blockchain)))))



(defn process-user-input [] 
  (println "1. Print Blockchain")
  (println "2. Add Transaction")
  (println "^C to Exit")
  (println "Enter your choice: ")
  (let [choice (read-line)]
    (cond
      (= choice "1") (print-blockchain)
      (= choice "2") (add-transaction)
      :else (do (println "Invalid choice. Please enter a valid option.") (process-user-input)))))

(defn -main [& args]
  (println "Blockchain B)")
  (loop []
    (process-user-input) 
      (recur)))

(-main)
