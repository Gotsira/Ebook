package com.example.got.ebook.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.got.ebook.R
import com.example.got.ebook.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_entry.view.*

class BookAdapter : BaseAdapter {
    var cart = ArrayList<Book>()
    var booksList = ArrayList<Book>()
    var context: Context? = null

    constructor(context: Context, booksList: ArrayList<Book>) : super() {
        this.context = context
        this.booksList = booksList
    }

    override fun getCount(): Int {
        return booksList.size
    }

    override fun getItem(position: Int): Any {
        return booksList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val book = this.booksList[position]

        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var bookView = inflator.inflate(R.layout.book_entry, null)
        Picasso.get().load(book.img_url).into(bookView.imgBook)
        bookView.bookName.text = book.title!!
        bookView.bookPrice.text = "$" + book.price!!.toString()
        Picasso.get().load("https://png.icons8.com/metro/1600/plus-math.png").resize(60, 55).into(bookView.plus!!)

        bookView.plus!!.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("Add " + book.title + " to your cart ?")
                    .setPositiveButton("ADD", DialogInterface.OnClickListener { dialog, id ->
                        if(!cart.contains(book))
                            cart.add(book)
                    })
                    .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create().show()
            notifyDataSetChanged()
        }

        return bookView
    }
}