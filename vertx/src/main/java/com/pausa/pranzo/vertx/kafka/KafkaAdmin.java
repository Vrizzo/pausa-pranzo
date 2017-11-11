package com.pausa.pranzo.vertx.kafka;

import org.apache.kafka.clients.admin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.util.Collections.singleton;

public class KafkaAdmin {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaAdmin.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final AdminClient adminClient = AdminClient.create(createProperties());//use kafka distribution scripts to start the broker

        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(createDescribeTopicsRequest());
        boolean match = existsTopic(describeTopicsResult);

        if (match) {
            LOG.info("Topic " + KafkaTopics.EXAMPLE.getTopicName() + " already exists ... skipping topic creation");
            return;
        }
        adminClient.createTopics(createTopicsRequest());
        LOG.info("Topic " + KafkaTopics.EXAMPLE.getTopicName() + " created");

    }

    private static Map<String, Object> createProperties() {
        return new HashMap<String, Object>() {{
            put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            put(AdminClientConfig.CLIENT_ID_CONFIG, "kafka-admin-client");
        }};
    }

    private static boolean existsTopic(DescribeTopicsResult describeTopicsResult) {
        try {
            return describeTopicsResult.all().get().entrySet().stream()
                    .map(Map.Entry::getValue)
                    .map(TopicDescription::name)
                    .anyMatch(KafkaTopics.EXAMPLE.getTopicName()::equals);
        } catch (Exception e) {
            LOG.error("Error describing topics", e);
            return false;
        }
    }

    private static Collection<String> createDescribeTopicsRequest() {
        return singleton(KafkaTopics.EXAMPLE.getTopicName());
    }

    private static Collection<NewTopic> createTopicsRequest() {
        return singleton(new NewTopic(KafkaTopics.EXAMPLE.getTopicName(), 1, (short) 1));// single node / single broker
    }
}
