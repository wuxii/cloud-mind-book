package com.mindbook

import com.mindbook.annotation.VertxBootApplication

@VertxBootApplication
open class MindbookApplication

fun main(vararg args: String) {
    VertxApplication.boot(MindbookApplication::class, *args)
}
