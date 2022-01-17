package com.example.f.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Local.BookFavEntity
import com.example.f.R
import com.example.f.databinding.FavoriteRecyclerBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var favList = ArrayList<BookFavEntity>()

    class FavoriteViewHolder(private val binding: FavoriteRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: BookFavEntity) {
            Picasso.get()
                .load(entity.thumbnail)
                .error(R.drawable.ic_no_thumbnail)
                .fit()
                .into(binding.favoriteThumbnail)
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