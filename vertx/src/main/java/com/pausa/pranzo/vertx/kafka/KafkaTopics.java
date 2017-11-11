package com.pausa.pranzo.vertx.kafka;

public enum KafkaTopics {

    EXAMPLE("kafka-topic");

    private final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
