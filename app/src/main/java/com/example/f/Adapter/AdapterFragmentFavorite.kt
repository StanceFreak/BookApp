package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.ItemDetailActivity
import com.example.f.Model.Local.BookFavEntity
import com.example.f.Model.ItemDetail
import com.example.f.Model.Model.Item
import com.example.f.R
import com.example.f.databinding.FavoriteRecyclerBinding
import com.squareup.picasso.Picasso

class AdapterFragmentFavorite: RecyclerView.Adapter<AdapterFragmentFavorite.FavoriteViewHolder>() {

    private var favList = ArrayList<BookFavEntity>()

    class FavoriteViewHolder(private val binding: FavoriteRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: BookFavEntity) {
            if(entity.thumbnail != null) {
                Picasso.get()
                        .load(entity.thumbnail)
                        .error(R.drawable.ic_book_icon)
                        .placeholder(R.drawable.ic_no_thumbnail)
                        .fit()
                        .into(binding.favoriteThumbnail)
            }
            else {
                Picasso.get()
                        .cancelRequest(binding.favoriteThumbnail)
                binding.favoriteThumbnail.setBackgroundResource(R.drawable.ic_book_icon)
            }
            binding.favoriteTitle.text = entity.title
            binding.favoriteThumbnail.clipToOutline = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding = FavoriteRecyclerBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = favList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, ItemDetailActivity::class.java)
            val itemDetail = ItemDetail(
                    data.bookId,
                    data.thumbnail,
                    data.title,
                    data.author.toString(),
                    data.averageRating,
                    data.ratingCount,
                    data.desc.toString(),
                    data.pageCount,
                    data.previewLink
            )
            i.putExtra(ItemDetailActivity.BOOK_DETAIL, itemDetail)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    fun setFavorite(favList : List<BookFavEntity>) {
        this.favList.clear()
        this.favList.addAll(favList)
        notifyDataSetChanged()
    }
}