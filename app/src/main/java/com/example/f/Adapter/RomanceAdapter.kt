package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.Model.ItemDetail
import com.example.f.R
import com.example.f.Model.Romance.Item
import com.example.f.databinding.RecyclerItemBooksBinding
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class RomanceAdapter: RecyclerView.Adapter<RomanceAdapter.RomanceViewHolder>() {

//    private var filteredData = ArrayList<Item>()
    private var data = ArrayList<Item> ()

    inner class RomanceViewHolder(private val binding: RecyclerItemBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            if (item.volumeInfo != null) {
                Picasso.get()
                    .load(item.volumeInfo.imageLinks?.thumbnail)
                    .placeholder(R.drawable.ic_no_thumbnail)
                    .error(R.drawable.ic_no_thumbnail)
                    .fit()
                    .into(binding.itemThumbnail)
            }

            binding.itemTitle.text = item.volumeInfo?.title
            binding.itemThumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RomanceViewHolder {
        val itemBinding = RecyclerItemBooksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return RomanceViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RomanceViewHolder, position: Int) {
        val data = this.data[position]
        holder.bind(data)

        holder.itemView.setOnClickListener{
            val itemDetail = ItemDetail(
                    data.id,
                    data.volumeInfo!!.imageLinks?.thumbnail,
                    data.volumeInfo.title,
                    data.volumeInfo.authors.toString(),
                    data.volumeInfo.averageRating,
                    data.volumeInfo.ratingsCount,
                    data.volumeInfo.description.toString(),
                    data.volumeInfo.pageCount,
                    data.volumeInfo.previewLink
            )
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, itemDetail)
            holder.itemView.context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(bookList: List<Item>) {
        this.data.clear()
        this.data.addAll(bookList)
        notifyDataSetChanged()
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence?): FilterResults {
//                val queryString = charSequence.toString()
//
//                if (queryString.isEmpty()) {
//                    filteredData = data
//                }
//                else {
//                    val tempFilteredData = ArrayList<Item>()
//
//                    for (item in data) {
//                        if (item.volumeInfo?.title?.toLowerCase(Locale.ROOT)!!
//                                .contains(
//                                        queryString,
//                                        ignoreCase = true
//                                )
//                            ) {
//                            tempFilteredData.add(item)
//                        }
//                    }
//                    filteredData = tempFilteredData
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = filteredData
//                return filterResults
//
//            }
//
//            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
//                filteredData = filterResults!!.values as ArrayList<Item>
//                Log.d("publishResults", filteredData.toString())
//                notifyDataSetChanged()
//            }
//
//        }
//    }



}