package com.mindbook

import com.mindbook.annotation.Verticles
import com.mindbook.verticle.BookVerticle
import com.mindbook.verticle.GatewayVerticle

@Verticles([GatewayVerticle::class, BookVerticle::class])
object MindbookApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        VertxApplication.boot(MindbookApplication::class, args)
    }

}