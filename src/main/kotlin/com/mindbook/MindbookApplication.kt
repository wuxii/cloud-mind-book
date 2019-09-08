package com.mindbook

import com.mindbook.verticle.BookVerticle
import com.mindbook.verticle.GatewayVerticle
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlin.reflect.KClass

fun main(args: Array<String>) {
    VertxApplication.boot(MindbookApplication::class, args)
}

@Verticles([GatewayVerticle::class, BookVerticle::class])
object MindbookApplication

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Verticles(val classes: Array<KClass<out CoroutineVerticle>> = [])

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class DeploymentConfiguration(val path: String = "")

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class VerticleClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Path(val value: String = "")