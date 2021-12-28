package com.example.f.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.f.R
import com.squareup.picasso.Picasso

class ItemDetailActivity : AppCompatActivity() {

    companion object {
        const val BOOK_DETAIL = "book_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item_activity)

        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val bookExtras = intent.getParcelableExtra<com.example.f.ModelRomance.Item>(BOOK_DETAIL)

        val thumbnail = findViewById<ImageView>(R.id.detail_thumbnail)
        val title = findViewById<TextView>(R.id.detail_title)
        val author = findViewById<TextView>(R.id.detail_author)
        val rating = findViewById<RatingBar>(R.id.detail_average_rating)
        val ratingCount = findViewById<TextView>(R.id.detail_rating_count)
        val description = findViewById<TextView>(R.id.detail_desc)
        val webview = findViewById<LinearLayout>(R.id.detail_webview)
        val page = findViewById<TextView>(R.id.detail_pages)

        if (bookExtras != null) {
            title.text = bookExtras.volumeInfo?.title
            author.text = bookExtras.volumeInfo?.authors.toString()
            rating.rating = bookExtras.volumeInfo!!.averageRating
            ratingCount.text = bookExtras.volumeInfo.averageRating.toString()
            description.text = bookExtras.volumeInfo.description
            page.text = bookExtras.volumeInfo.pageCount.toString()
            Picasso.get()
                .load(bookExtras.volumeInfo.imageLinks?.thumbnail)
                .error(R.drawable.ic_no_thumbnail)
                .fit()
                .into(thumbnail)
            webview.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(bookExtras.volumeInfo.previewLink))
                startActivity(i)
            }
        }
    }
}