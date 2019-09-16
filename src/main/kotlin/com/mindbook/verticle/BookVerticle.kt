package com.mindbook.verticle

import com.mindbook.AutoMappingCoroutineVerticle
import com.mindbook.annotation.Path
import com.mindbook.annotation.Verticle
import com.mindbook.service.BookService
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject
import org.springframework.beans.factory.annotation.Autowired

@Verticle
open class BookVerticle : AutoMappingCoroutineVerticle() {

    @Autowired
    private lateinit var bookService: BookService

    @Path("/book/info")
    suspend fun getBookInfo(message: Message<JsonObject>): JsonObject {
        val name = message.body().getString("name", "default")
        val book = bookService.getBookInfo(name)
        return JsonObject.mapFrom(book)
    }

}