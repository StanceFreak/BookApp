package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.Model.ItemDetail
import com.example.f.Model.BookModel.Item
import com.example.f.R
import com.example.f.databinding.RecyclerItemBooksBinding
import com.squareup.picasso.Picasso

class AdapterItemGeorge: RecyclerView.Adapter<AdapterItemGeorge.ViewHolder>() {

    private var data = ArrayList<Item>()

    class ViewHolder(private val binding: RecyclerItemBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            if(item.volumeInfo?.imageLinks?.thumbnail != null) {
                Picasso.get()
                        .load(item.volumeInfo.imageLinks.thumbnail)
                        .error(R.drawable.ic_book_icon)
                        .placeholder(R.drawable.ic_no_thumbnail)
                        .fit()
                        .into(binding.itemThumbnail)
            }
            else {
                Picasso.get()
                        .cancelRequest(binding.itemThumbnail)
                binding.itemThumbnail.setBackgroundResource(R.drawable.ic_book_icon)
            }

            binding.itemTitle.text = item.volumeInfo?.title
            binding.itemThumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RecyclerItemBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
        return data.size
    }

    fun setData(bookList: List<Item>) {
        this.data.clear()
        this.data.addAll(bookList)
        notifyDataSetChanged()
    }

}