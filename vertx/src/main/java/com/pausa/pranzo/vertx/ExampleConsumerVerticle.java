package com.pausa.pranzo.vertx;

import io.vertx.core.*;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleConsumerVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleConsumerVerticle.class);

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("vertx-consumer", message -> LOG.info("Vertx consumer received msg: " + message.body()));
    }
}
