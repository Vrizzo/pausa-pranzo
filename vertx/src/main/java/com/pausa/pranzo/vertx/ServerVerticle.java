package com.pausa.pranzo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(ServerVerticle.class);
    private HttpServer server;

    @Override
    public void start() throws Exception {
        super.start();
        server = vertx.createHttpServer();
        server.requestHandler(request -> {
            // This handler gets called for each request that arrives on the server because no router is configured
            //eg: http://localhost:8081/everything

            LOG.info("Publishing request path to kafka");
            vertx.eventBus().send("kafka-producer", request.path());

            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");
            // Write to the response and end it
            response.end("OK");
        });

        server.listen(8081);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        server.close();
    }
}
