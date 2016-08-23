(ns assembly.core
  (:require [cognitect.transit :as transit])
  (:import [java.io ByteArrayInputStream ByteArrayOutputStream]
           [org.apache.kafka.clients.producer Producer KafkaProducer ProducerRecord]))

(defn- payload->transit
  [x]
  (let [baos (ByteArrayOutputStream.)
        w    (transit/writer baos :json)
        _    (transit/write w x)
        ret  (.toString baos)]
    (.reset baos)
    ret))

(def key-serializer
  "org.apache.kafka.common.serialization.StringSerializer")

(def value-serializer
  "org.apache.kafka.common.serialization.StringSerializer")

(defn- producer
  []
  (let [props (java.util.Properties.)]
    (.setProperty props "key.serializer" key-serializer)
    (.setProperty props "value.serializer" value-serializer)
    (.setProperty props "bootstrap.servers" "default:9092")
    (KafkaProducer. props)))

(defn- record
  [^String topic ^String key ^String payload]
  (ProducerRecord. topic key payload))

(defn- publish
  [^KafkaProducer producer ^ProducerRecord record]
  (.send producer record))

(defn drop
  [topic key payload]
  (publish (producer) (record topic key (payload->transit payload))))
