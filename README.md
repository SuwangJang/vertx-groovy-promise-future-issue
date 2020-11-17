# Vert.X Groovy 'promise.future()' Issue Report

promise.future().onComplete(handler) does not be called randomly (It seems occur on build time).

Probably @CacheReturn, vertx.codegen annotation processor, causes it.

This example source shows such a strange behavior.

Just run "loop.sh" (It will be stopped when future().onComplete() was not called.)

