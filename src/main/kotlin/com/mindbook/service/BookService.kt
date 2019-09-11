package com.mindbook.service

import com.mindbook.domain.Book

class BookService {

    fun getBookInfo(name: String): Book {
        return Book("book")
    }

}