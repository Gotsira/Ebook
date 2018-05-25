package com.example.got.ebook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.got.ebook.adapter.BookAdapter
import com.example.got.ebook.model.Book
import com.example.got.ebook.model.BookRepository
import com.example.got.ebook.model.DataBookRepository
import com.example.got.ebook.presenter.BookPresenter
import com.example.got.ebook.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    var adapter: BookAdapter? = null
    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository
    private var spinnerAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository = DataBookRepository()
        presenter = BookPresenter(this, repository)
        presenter.start()
        setSearchView()
        setSpinner()
    }

    override fun setBookList(books: ArrayList<Book>) {
        adapter = BookAdapter(this, books)
        books_lv.adapter = adapter
    }

    private fun setSearchView() {
        search.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    presenter.search(search.query.toString(), sort.selectedItem.toString())
                return false
            }
        })
    }

    private fun setSpinner() {
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListOf("UNSORTED", "TITLE", "YEAR"))
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sort.adapter = spinnerAdapter
        sort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.search(search.query.toString(), sort.selectedItem.toString())
            }
        }
    }
}
