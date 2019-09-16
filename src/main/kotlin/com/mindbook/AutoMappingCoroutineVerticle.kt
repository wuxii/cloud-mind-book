package com.mindbook

import com.mindbook.annotation.Path
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import kotlin.reflect.KCallable
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.findAnnotation

open class AutoMappingCoroutineVerticle : CoroutineVerticle() {

    protected val log = LoggerFactory.getLogger(this::class.java)

    override suspend fun start() {
        val members = this::class.members
        for (member in members) {
            val address = member.findAnnotation<Path>()?.value
            if (address != null && address.isNotBlank()) {
                bind(address, member)
            }
        }
        doStart()
    }

    open suspend fun doStart() {

    }

    protected fun bind(address: String, member: KCallable<*>) {
        log.info("[{}] bind service: {}", address, member)
        val verticle = this
        vertx.eventBus().consumer<JsonObject>(address) { request ->
            launch(vertx.dispatcher()) {
                try {
                    val response = member.callSuspend(verticle, request)
                    log.info("response: {}", response)
                    request.reply(response)
                } catch (e: Throwable) {
                    log.error("some exception happen.", e)
                    request.reply(JsonObject().put("error", 0).put("message", e.message))
                }
            }
        }
    }

}