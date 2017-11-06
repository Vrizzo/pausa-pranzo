package com.pausa.pranzo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class PausaPranzoApp {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();//cannot inline this ... every call creates a new independent instance of vertx
        vertx.deployVerticle(new ExampleConsumerVerticle());
        vertx.deployVerticle(new ExampleProducerVerticle());
    }
}
