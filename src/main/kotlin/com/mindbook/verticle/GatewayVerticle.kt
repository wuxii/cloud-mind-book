package com.mindbook.verticle

import io.vertx.core.http.HttpConnection
import io.vertx.core.http.HttpServerRequest
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.eventbus.requestAwait
import io.vertx.kotlin.core.http.endAwait
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GatewayVerticle : CoroutineVerticle() {

    companion object {
        const val PORT: Int = 8080
        var log: Logger = LoggerFactory.getLogger(GatewayVerticle::class.java)
    }

    override suspend fun start() {
        vertx.createHttpServer()
                .requestHandler(this::mappingRequestHandler)
                .exceptionHandler(this::globalExceptionHandler)
                .connectionHandler(this::connectionHandler)
                .listenAwait(PORT)
        log.info("http server listener at port: {}", PORT)
    }


    private fun mappingRequestHandler(request: HttpServerRequest) {
        val path = request.path()
        val response = request.response()
        launch {
            val message = convertRequestMessage(request)
            log.info("[{}] handle request: {}", path, message)
            val responseMessage = vertx.eventBus().requestAwait<JsonObject>(path, message)
            response.endAwait(responseMessage.toString())
        }
    }

    private fun globalExceptionHandler(exception: Throwable) {

    }

    private fun connectionHandler(connection: HttpConnection) {

    }

    private fun convertRequestMessage(request: HttpServerRequest): JsonObject {
        val message = JsonObject()
        message.put("address", request.path())
        return message
    }

}