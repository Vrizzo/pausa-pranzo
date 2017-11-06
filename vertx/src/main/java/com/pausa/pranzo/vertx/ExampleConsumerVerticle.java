package com.pausa.pranzo.vertx;

import io.vertx.core.*;
import io.vertx.core.eventbus.Message;

public class ExampleConsumerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("example", message -> {
            System.out.println("Received msg: " + message.body());
        });
        System.out.println("Consumer started");
    }
}
