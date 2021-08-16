package com.example.f.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Model.Item
import com.example.f.R
import com.squareup.picasso.Picasso

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

//    private var list = emptyList<Item>()
//    private var list = ArrayList<Item>()
    private lateinit var list : List<Item>

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView
        var rating: RatingBar
        var price: TextView

        init {
            image = view.findViewById(R.id.popular_thumbnail)
            title = view.findViewById(R.id.popular_title)
            author = view.findViewById(R.id.popular_author)
            rating = view.findViewById(R.id.popular_rating)
            price = view.findViewById(R.id.popular_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.popular_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = list.get(position)
        Picasso.get()
            .load(data.volumeInfo.imageLinks.thumbnail)
            .into(holder.image)
        holder.title.text = data.volumeInfo.title
        holder.author.text = data.volumeInfo.authors.toString()
        holder.rating.rating = data.volumeInfo.averageRating.toFloat()
        holder.price.text = data.saleInfo.retailPrice.amount.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(bookList: List<Item>) {
        this.list = bookList
        notifyDataSetChanged()
    }

}