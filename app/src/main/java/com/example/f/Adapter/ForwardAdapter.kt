package com.example.f.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.f.R
import com.example.f.Model.Model.Item
import com.example.f.databinding.RecyclerSeeAllBinding
import com.squareup.picasso.Picasso

class ForwardAdapter : RecyclerView.Adapter<ForwardAdapter.ForwardViewHolder>() {

    private var data = ArrayList<Item>()

    class ForwardViewHolder(private val binding: RecyclerSeeAllBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            if (item.volumeInfo?.imageLinks != null) {
                Picasso.get()
                    .load(item.volumeInfo.imageLinks.thumbnail)
                    .placeholder(R.drawable.ic_no_thumbnail)
                    .error(R.drawable.ic_no_thumbnail)
                    .fit()
                    .into(binding.thumbnail)
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

        holder.itemView.setOnClickListener {  }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }


}