package com.pausa.pranzo.vertx;

import io.vertx.core.AbstractVerticle;

import java.util.concurrent.atomic.AtomicInteger;

public class ExampleProducerVerticle extends AbstractVerticle {

    private static final int TIMEOUT_IN_MILLIS = 100;
    private final AtomicInteger counter;

    public ExampleProducerVerticle() {
        counter = new AtomicInteger(1);
    }

    @Override
    public void start() throws Exception {
        sendMessageWithFixedTimeout();
        System.out.println("Producer started");
    }

    private void sendMessageWithFixedTimeout() {
        vertx.setPeriodic(TIMEOUT_IN_MILLIS, this::sendMEssage);
    }

    private void sendMEssage(long ignoredTimerId) {
        vertx.eventBus().send("example", "Test" + counter.getAndIncrement());
    }
}
