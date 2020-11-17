package org.example.vertx.promise

import io.vertx.core.*

class PromiseFutureGroovy {

    static class TestVerticle extends AbstractVerticle {
        @Override
        void start(Promise<Void> startPromise) throws Exception {

            //@CacheReturn promise.future() not be called randomly.
            Promise promise1 = Promise.promise()
            promise1.future().onComplete { event ->
                println("promise1: future().onComplete() : quit.")
                startPromise.complete()
                System.exit(0)
            }

            //No problem here
            Promise promise2 = Promise.promise()
            ((Future) promise2).onComplete { event ->
                println("promise2: ((Future) promise).onComplete() : stuck!")
            }

            promise1.complete()
            promise2.complete()

            println("promise1.future().onComplete{} was not called.")
            startPromise.complete()
        }
    }

    static void main(String[] args) {
        Vertx.vertx().deployVerticle(TestVerticle.class, new DeploymentOptions())
    }
}
