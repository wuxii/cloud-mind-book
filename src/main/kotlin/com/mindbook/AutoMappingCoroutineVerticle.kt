package com.mindbook

import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch
import kotlin.reflect.KCallable
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.findAnnotation

open class AutoMappingCoroutineVerticle : CoroutineVerticle() {

    override suspend fun start() {
        val members = this::class.members
        for (member in members) {
            val value = member.findAnnotation<Path>()?.value
            if (value != null && value.isNotBlank()) {
                doMapping(value, member)
            }
        }
        doStart()
    }

    protected fun doMapping(path: String, member: KCallable<*>) {
        val verticle = this
        vertx.eventBus()
                .consumer<Message<JsonObject>>(path) { msg ->
                    launch(vertx.dispatcher()) {
                        // TODO call suspend
                        // member.parameters
                        member.callSuspend(this, msg)
                        member::class.java
                        // member.call(msg, verticle, this)
                    }
                }
    }

    open suspend fun doStart() {

    }

}