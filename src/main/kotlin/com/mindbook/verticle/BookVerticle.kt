package com.mindbook.verticle

import com.mindbook.AutoMappingCoroutineVerticle
import com.mindbook.DeploymentConfiguration
import com.mindbook.Path
import com.mindbook.service.Book
import com.mindbook.service.BookService
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject

@DeploymentConfiguration
open class BookVerticle : AutoMappingCoroutineVerticle() {

    companion object {
        private lateinit var bookService: BookService
    }

    override suspend fun doStart() {
    }

    @Path("/book/info")
    suspend fun getBookInfo(message: Message<JsonObject>): Book {
        return bookService.getBookInfo(message.body().getString("name"))
    }

}