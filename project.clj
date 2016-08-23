(defproject assembly "0.1.5"
  :description "Drops transit data onto a Kafka topic."
  :url "https://github.com/optimis/assembly"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.cognitect/transit-clj "0.8.288"]
                 [org.apache.kafka/kafka-clients "0.10.0.1"]])
