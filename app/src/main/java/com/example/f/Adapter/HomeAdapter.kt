package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.ModelRomance.Item
import com.example.f.R
import com.squareup.picasso.Picasso

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var data : List<Item>? = listOf()

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView

        init {
            image = view.findViewById(R.id.popular_thumbnail)
            title = view.findViewById(R.id.popular_title)

            image.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.romance_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = data!!.get(position)
        holder.title.text = data.volumeInfo.title

        @Suppress("ConstantConditionIf")
        if(data.volumeInfo.imageLinks != null) {
            Picasso.get()
                .load(data.volumeInfo.imageLinks.thumbnail)
                .error(R.drawable.ic_no_thumbnail)
                .fit()
                .into(holder.image)
        }

        holder.itemView.setOnClickListener{
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, data.id)
            holder.itemView.context.startActivity(i)
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