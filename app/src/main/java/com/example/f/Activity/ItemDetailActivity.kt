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

                binding.toggleFav.setOnCheckedChangeListener {
                        compoundButton: CompoundButton,
                        isChecked: Boolean ->

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

                    if(isChecked) {
                        isBookmark = true
                        viewModel.addFav(favData)
                        binding.toggleFav.setBackgroundResource(R.drawable.ic_favorite_filled)
                        binding.tvBookmarkDetail.text = "Bookmarked"
                        saveState(isBookmark)


                    }
                    else if (!isChecked) {
                        isBookmark = false
                        val e = Exception()
                        e.printStackTrace()
                        viewModel.deleteFav(favData.id)
                        binding.toggleFav.setBackgroundResource(R.drawable.ic_favorite_outline)
                        binding.tvBookmarkDetail.text = "Add to favorite"
                        saveState(isBookmark)
                    }
                }
            }
        }
        val sp = getSharedPreferences("button_state", Context.MODE_PRIVATE)
        val checkState = sp.getBoolean("state" + bookExtras!!.id, false)

        if(checkState) {
            binding.toggleFav.setBackgroundResource(R.drawable.ic_favorite_filled)
            binding.tvBookmarkDetail.text = "Bookmarked"
        }
        else if (!checkState){

            binding.toggleFav.setBackgroundResource(R.drawable.ic_favorite_outline)
            binding.tvBookmarkDetail.text = "Add to favorite"
        }
    }

    private fun saveState(isBookmark: Boolean) {
        val bookExtras = intent.getParcelableExtra<Item>(BOOK_DETAIL)
        val sp = getSharedPreferences("button_state", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean("state" + bookExtras!!.id, isBookmark)
        editor.apply()
    }

//    private fun checkState() {
//        sp.getBoolean("state", true)
//    }
}