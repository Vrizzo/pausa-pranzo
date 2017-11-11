package com.pausa.pranzo.vertx.kafka;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerExample extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerExample.class);
    private Producer<String, String> producer;


    @Override
    public void start() throws Exception {
        super.start();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);

        vertx.eventBus().consumer("kafka-producer", this::publishToKafka);
    }

    private void publishToKafka(Message<String> message) {
        producer.send(new ProducerRecord<>(KafkaTopics.EXAMPLE.getTopicName(), message.body()));
    }

    @Override
    public void stop() throws Exception {
        producer.close();
        super.stop();
    }

    public static void main(String[] args) throws Exception {

        KafkaProducerExample producer = new KafkaProducerExample();
        producer.start();
        producer.stop();

    }
}
