package com.example.got.ebook.model

import java.io.Serializable

class Book(val title: String, val id: Int, val price: Double = 0.0, val pub_year: Int = 0, val img_url: String = ""): Serializable {

    override fun toString(): String {
        return String.format("%s (%d)", title, pub_year)
    }

}