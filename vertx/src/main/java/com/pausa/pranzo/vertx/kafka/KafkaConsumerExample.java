package com.pausa.pranzo.vertx.kafka;

import io.vertx.core.AbstractVerticle;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class KafkaConsumerExample extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerExample.class);

    private boolean poll;
    private Consumer<String, String> consumer;

    public KafkaConsumerExample() {
        poll = true;
    }

    @Override
    public void start() throws Exception {
        super.start();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaTopics.EXAMPLE.getTopicName()));


//        vertx.executeBlocking(event -> {
            while (poll) {
                consumer.poll(100).forEach(this::publish);
            }
//            event.complete();
//        }, result -> LOG.info("Kafka consumer polling terminated"));

    }

    @Override
    public void stop() throws Exception {
        poll = false;
        consumer.close(3, TimeUnit.SECONDS);
        super.stop();
    }

    private void publish(ConsumerRecord<String, String> record) {
        LOG.info(format("Reading msg: offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()));
        vertx.eventBus().send("vertx-consumer", record.value());
    }

    public static void main(String[] args) throws Exception {
        KafkaConsumerExample consumer = new KafkaConsumerExample();
        consumer.start();
    }
}
