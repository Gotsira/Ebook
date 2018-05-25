package com.example.got.ebook.model

class MockBookRepository: BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book("BOOK1",1,  200.0, 2011, "https://imagery.pragprog.com/products/471/lhelph_largebeta.jpg"))
        bookList.add(Book("BOOK2",2,  300.0,2016, "https://imagery.pragprog.com/products/444/rspec3_largebeta.jpg"))
        bookList.add(Book("BOOK3",3,  400.0,2013, "https://imagery.pragprog.com/products/446/dswdcloj2_largecover.jpg"))
        setChanged()
        notifyObservers()
    }
}