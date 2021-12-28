package com.example.f.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.ModelRomance.Item
import com.example.f.R
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(), Filterable {

    private var filteredData = ArrayList<Item>()
    private var data = ArrayList<Item> ()


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
        return HomeViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.romance_recycler, parent, false))

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = filteredData.get(position)
        holder.title.text = data.volumeInfo?.title

        @Suppress("ConstantConditionIf")
        if(data.volumeInfo?.imageLinks != null) {
            Picasso.get()
                .load(data.volumeInfo.imageLinks.thumbnail)
                .error(R.drawable.ic_no_thumbnail)
                .fit()
                .into(holder.image)
        }

        holder.itemView.setOnClickListener{
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, data)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence.toString()

                if (queryString.isEmpty()) {
                    filteredData = data
                }
                else {
                    val tempFilteredData = ArrayList<Item>()

                    for (item in data) {
                        if (item.volumeInfo?.title?.toLowerCase(Locale.ROOT)!!.contains(queryString.toLowerCase(Locale.ROOT))) {
                            tempFilteredData.add(item)
                        }
                    }
                    filteredData = tempFilteredData
                }

                val filterResults = FilterResults()
                filterResults.values = filteredData
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                filteredData = filterResults?.values as ArrayList<Item>
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    fun setData(bookList: ArrayList<Item>) {
        this.data = bookList
        filteredData = data
        notifyDataSetChanged()
    }

}