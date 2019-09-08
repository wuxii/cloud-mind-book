package com.mindbook.service

class BookService {

    fun getBookInfo(name: String): Book {
        return Book("book")
    }

}

data class Book(val name: String)