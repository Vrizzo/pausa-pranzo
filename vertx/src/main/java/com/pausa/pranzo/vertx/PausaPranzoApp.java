package com.pausa.pranzo.vertx;

import com.pausa.pranzo.vertx.kafka.KafkaConsumerExample;
import com.pausa.pranzo.vertx.kafka.KafkaProducerExample;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class PausaPranzoApp {

    private static final long ONE_HOUR_IN_NANO_SECONDS = 24L * 60 * 60 * 1000 * 1000000;

    public static void main(String[] args) {
        //cannot inline this ... every call creates a new independent instance of vertx (something like deploying onto 2 different servers)
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());
        vertx.deployVerticle(new ExampleConsumerVerticle());
        vertx.deployVerticle(new KafkaProducerExample());

        // set as worker because it block the event loop ... blocking the event loop, stuck all the application
        vertx.deployVerticle(new KafkaConsumerExample(), createDeploymentOptions());
    }

    private static DeploymentOptions createDeploymentOptions() {
        return new DeploymentOptions().setWorker(true).setMaxWorkerExecuteTime(ONE_HOUR_IN_NANO_SECONDS);//default is 1 minute
    }

}
