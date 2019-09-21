package com.mindbook.verticle

import com.mindbook.AutoMappingCoroutineVerticle
import com.mindbook.annotation.Path
import com.mindbook.annotation.Verticle
import com.mindbook.domain.Book
import com.mindbook.service.BookService
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject

@Verticle
open class BookVerticle(private val bookService: BookService) : AutoMappingCoroutineVerticle() {

    @Path("/book/info")
    suspend fun getBookInfo(message: Message<JsonObject>): Book {
        val name = message.body().getString("name", "default")
        return bookService.getBookInfo(name)
    }

}