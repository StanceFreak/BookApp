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
import com.example.f.Fragment.HomeFragment
import com.example.f.ModelRomance.Item
import com.example.f.R
import com.example.f.databinding.RomanceRecyclerBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(), Filterable {

    private var filteredData = ArrayList<Item>()
    private var data = ArrayList<Item> ()


    inner class HomeViewHolder(private val binding: RomanceRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            if (item.volumeInfo?.imageLinks != null) {
                Picasso.get()
                    .load(item.volumeInfo.imageLinks.thumbnail)
                    .error(R.drawable.ic_no_thumbnail)
                    .fit()
                    .into(binding.popularThumbnail)
            }

            binding.popularTitle.text = item.volumeInfo?.title
            binding.popularThumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = RomanceRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return HomeViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val data = filteredData[position]
        holder.bind(data)

        holder.itemView.setOnClickListener{
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, data)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    fun setData(bookList: List<Item>) {
        this.data.clear()
        this.data.addAll(bookList)
        this.filteredData.clear()
        this.filteredData.addAll(bookList)
        notifyDataSetChanged()
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
                        if (item.volumeInfo?.
                                title?.
                                toLowerCase(Locale.ROOT)!!.
                                contains(
                                        queryString,
                                        ignoreCase = true
                                )
                            ) {
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
                filteredData = filterResults!!.values as ArrayList<Item>
                Log.d("publishResults", filteredData.toString())
                notifyDataSetChanged()
            }

        }
    }



}