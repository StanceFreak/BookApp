package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.Model.ItemDetail
import com.example.f.R
import com.example.f.Model.BookModel.Item
import com.example.f.databinding.RecyclerSeeAllBinding
import com.squareup.picasso.Picasso

class AdapterActivitySeeAll : RecyclerView.Adapter<AdapterActivitySeeAll.ForwardViewHolder>() {

    private var data = ArrayList<Item>()

    class ForwardViewHolder(private val binding: RecyclerSeeAllBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            if(item.volumeInfo?.imageLinks?.thumbnail != null) {
                Picasso.get()
                        .load(item.volumeInfo.imageLinks.thumbnail)
                        .error(R.drawable.ic_book_icon)
                        .placeholder(R.drawable.ic_no_thumbnail)
                        .fit()
                        .into(binding.thumbnail)
            }
            else {
                Picasso.get()
                        .cancelRequest(binding.thumbnail)
                binding.thumbnail.setBackgroundResource(R.drawable.ic_book_icon)
            }
            binding.title.text = item.volumeInfo?.title
            binding.thumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForwardViewHolder {
        val itemBinding = RecyclerSeeAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForwardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForwardViewHolder, position: Int) {
        val data = this.data[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            val itemDetail = ItemDetail(
                    data.id,
                    data.volumeInfo!!.imageLinks?.thumbnail,
                    data.volumeInfo.title,
                    data.volumeInfo.authors.toString(),
                    data.volumeInfo.averageRating,
                    data.volumeInfo.ratingsCount,
                    data.volumeInfo.description.toString(),
                    data.saleInfo?.offers?.retailPrice?.amountInMicros,
                    data.volumeInfo.pageCount,
                    data.volumeInfo.previewLink
            )
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, itemDetail)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun setData(item: List<Item>) {
        this.data.clear()
        this.data.addAll(item)
        notifyDataSetChanged()
    }


}