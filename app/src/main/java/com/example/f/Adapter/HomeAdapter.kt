package com.example.f.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.f.ModelRomance.Item
import com.example.f.R
import com.squareup.picasso.Picasso

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var data : List<Item>? = listOf()

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var author: TextView
        var ratingbar: RatingBar
        var rate: TextView
        var description: TextView
        var price: TextView

        init {
            image = view.findViewById(R.id.popular_thumbnail)
            title = view.findViewById(R.id.popular_title)
            author = view.findViewById(R.id.popular_author)
            ratingbar = view.findViewById(R.id.popular_rating_bar)
            rate = view.findViewById(R.id.popular_rate)
            description = view.findViewById(R.id.popular_desc)
            price = view.findViewById(R.id.popular_price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.romance_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = data!!.get(position)
        holder.title.text = data.volumeInfo.title
        holder.author.text = data.volumeInfo.authors.toString().replace("""[\p{P}\p{S}&&[^.]]+""".toRegex(), "")
        holder.ratingbar.rating = data.volumeInfo.averageRating
        holder.rate.text = data.volumeInfo.averageRating.toString()
        holder.description.text = data.volumeInfo.description
        holder.price.text = String.format("Free")

        @Suppress("ConstantConditionIf")
        if(data.volumeInfo.imageLinks != null) {
            Picasso.get()
                .load(data.volumeInfo.imageLinks.thumbnail)
                .error(R.drawable.ic_no_thumbnail)
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    fun setData(bookList: List<Item>) {
        this.data = bookList
        notifyDataSetChanged()
    }

}