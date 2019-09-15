package com.mindbook.vertx

import io.vertx.core.Vertx
import org.springframework.context.ConfigurableApplicationContext

class VertxApplicationContext(val applicationContext: ConfigurableApplicationContext) {

    fun getVertx(): Vertx {
        return applicationContext.getBean(Vertx::class.java)
    }

}