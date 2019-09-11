package com.mindbook.verticle

import com.mindbook.AutoMappingCoroutineVerticle
import com.mindbook.annotation.Path
import com.mindbook.service.BookService
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject

open class BookVerticle : AutoMappingCoroutineVerticle() {

    companion object {
        private lateinit var bookService: BookService
    }

    override suspend fun doStart() {
        bookService = BookService()
    }

    @Path("/book/info")
    suspend fun getBookInfo(message: Message<JsonObject>): JsonObject {
        val name = message.body().getString("name", "default")
        return JsonObject.mapFrom(bookService.getBookInfo(name))
    }

}