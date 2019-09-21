package com.mindbook.service

import com.mindbook.domain.Book
import io.vertx.kotlin.sqlclient.preparedQueryAwait
import io.vertx.sqlclient.SqlClient
import org.springframework.stereotype.Service

@Service
class BookService(private val sqlClient: SqlClient) {

    suspend fun getBookInfo(name: String): Book {
        return sqlClient.preparedQueryAwait("select * from book")
                .map { Book(it.getString("id"), it.getString("name")) }
                .getOrElse(0) { throw RuntimeException("book not found") }
    }

}