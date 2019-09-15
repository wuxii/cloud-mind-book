package com.mindbook.annotation

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class VertxBootApplication