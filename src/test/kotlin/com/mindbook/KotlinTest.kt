package com.mindbook

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.callSuspend

fun main() {
    val greeting = Greeting()

    val member = Greeting::class.members.iterator().next()
    val method = Greeting::class.java.declaredMethods[0]

    println(member)
    println(method)

    runBlocking {
        GlobalScope.launch {
            println(member.callSuspend(greeting, "foo"))
            println(method.invoke(greeting, "bar", this))
        }.join()
    }

}

class Greeting {

    suspend fun greeting(name: String): String {
        return "greeting $name"
    }

}