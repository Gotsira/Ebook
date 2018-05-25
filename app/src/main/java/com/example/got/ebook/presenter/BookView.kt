package com.example.got.ebook.presenter

import com.example.got.ebook.model.Book

interface BookView {
    fun setBookList(books: ArrayList<Book>)
}
