package org.example.vertx.promise;

import io.vertx.core.*;

public class PromiseFuture {

    public static class TestVerticle extends AbstractVerticle {

        @Override
        public void start(Promise<Void> startPromise) {

            Promise<Void> promise1 = Promise.promise();
            promise1.future().onComplete(event -> {
                System.out.println("promise1: future().onComplete() : quit.");
                startPromise.complete();
                System.exit(0);
            });


            Promise<Void> promise2 = Promise.promise();
            ((Future) promise2).onComplete(event -> {
                System.out.println("promise2: ((Future) promise).onComplete() : stuck!");
            });

            promise1.complete();
            promise2.complete();

            System.out.println("end of start()");
            startPromise.complete();
        }
    }


    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(TestVerticle.class, new DeploymentOptions());
    }
}
