package com.mindbook

import com.mindbook.vertx.VertxApplicationContext
import org.springframework.boot.SpringApplication
import kotlin.reflect.KClass

object VertxApplication {

    fun boot(primarySource: KClass<*>, vararg args: String): VertxApplicationContext {
        return VertxApplicationContext(SpringApplication.run(primarySource.java, *args))
    }

}