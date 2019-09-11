package com.mindbook.annotation

import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Verticles(val classes: Array<KClass<out CoroutineVerticle>> = [])