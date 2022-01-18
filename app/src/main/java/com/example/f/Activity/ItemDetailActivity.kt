package com.example.f.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.example.f.Local.BookFavDatabase
import com.example.f.Local.BookFavEntity
import com.example.f.ModelRomance.Item
import com.example.f.ModelRomance.RomanceBooks
import com.example.f.R
import com.example.f.ViewModel.BookFavViewModel
import com.example.f.databinding.DetailItemActivityBinding
import com.squareup.picasso.Picasso

class ItemDetailActivity: AppCompatActivity() {

    private lateinit var binding: DetailItemActivityBinding
    private lateinit var viewModel: BookFavViewModel


    companion object {
        const val BOOK_DETAIL = "book_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailItemActivityBinding.inflate(layoutInflater)

        setToolbar()
        setData()

        setContentView(binding.root)
    }

    private fun setToolbar() {
        binding.detailToolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
        }
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.detailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setData() {

        val bookExtras = intent.getParcelableExtra<Item>(BOOK_DETAIL)
        viewModel = ViewModelProviders.of(this).get(BookFavViewModel::class.java)

        val sp = getSharedPreferences("button_state", Context.MODE_PRIVATE)
        val checkState = sp.getBoolean("state" + bookExtras!!.id, false)

        if(checkState) {
            binding.toggleFav.isChecked = true
            binding.tvBookmarkDetail.text = "Bookmarked"
        }
        else{
            binding.toggleFav.isChecked = false
            binding.tvBookmarkDetail.text = "Add to favorite"
        }

        if (bookExtras != null) {

            binding.apply {
                Picasso.get()
                        .load(bookExtras.volumeInfo?.imageLinks?.thumbnail)
                        .error(R.drawable.ic_no_thumbnail)
                        .placeholder(R.drawable.ic_no_thumbnail)
                        .fit()
                        .into(binding.detailThumbnail)
                detailTitle.text = bookExtras.volumeInfo?.title
                detailAuthor.text = bookExtras.volumeInfo?.authors.toString()
                detailAverageRating.rating = bookExtras.volumeInfo!!.averageRating
                detailRatingCount.text = bookExtras.volumeInfo.ratingsCount.toString()
                detailDesc.text = bookExtras.volumeInfo.description
                detailPages.text = "${bookExtras.volumeInfo.pageCount} pages"
                binding.detailWebview.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(bookExtras.volumeInfo.previewLink))
                    startActivity(i)
                }

                binding.toggleFav.setOnClickListener {

                    val isBookmark : Boolean
                    val favData = BookFavEntity(
                        0,
                            bookExtras.id!!,
                            bookExtras.volumeInfo.title,
                            bookExtras.volumeInfo.authors.toString(),
                            bookExtras.volumeInfo.averageRating,
                            bookExtras.volumeInfo.ratingsCount,
                            bookExtras.volumeInfo.description.toString(),
                            bookExtras.volumeInfo.pageCount,
                            bookExtras.volumeInfo.imageLinks?.thumbnail
                    )

                    if(binding.toggleFav.isChecked) {
                        binding.toggleFav.isChecked = true
                        binding.tvBookmarkDetail.text = "Bookmarked"
                        isBookmark = true
                        val editor = sp.edit()
                        editor.putBoolean("state" + bookExtras.id, isBookmark)
                        editor.apply()
                        viewModel.addFav(favData)
                    }
                    else {
                        binding.toggleFav.isChecked = false
                        binding.tvBookmarkDetail.text = "Add to favorite"
                        isBookmark = false
                        val editor = sp.edit()
                        editor.putBoolean("state" + bookExtras.id, isBookmark)
                        editor.apply()
                        viewModel.deleteFav(favData.bookId)
                    }
                }
            }
        }
    }
}