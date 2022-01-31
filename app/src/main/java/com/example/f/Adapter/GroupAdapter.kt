package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.SeeAllActivity
import com.example.f.Model.ItemAll
import com.example.f.Model.Romance.BooksResponse
import com.example.f.databinding.RecyclerGroupBooksBinding

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.ParentViewHolder>() {

    private var bookList = ArrayList<BooksResponse>()

    class ParentViewHolder(binding: RecyclerGroupBooksBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        val title = binding.title
        val rv = binding.rvChild
        val groupItem = binding.groupItem
//            if (itemViewType == 0) {
//                binding.title.text = book.heading
//                val romanceAdapter = RomanceAdapter()
//                with(binding.rvChild) {
//                    layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//                    adapter = romanceAdapter
//                    setHasFixedSize(true)
//                }
//                binding.groupTitle.setOnClickListener {
//                    val i = Intent(itemView.context, SeeAllActivity::class.java)
//                    val seeAllData = ItemAll(book.items)
//                    i.putExtra(SeeAllActivity.EXTRAS_ROMANCE, seeAllData)
//                    itemView.context.startActivity(i)
//                }
//            }
//            else if (itemViewType == 1) {
//                binding.title.text = book.heading
//                val adventureAdapter = AdventureAdapter()
//                with(binding.rvChild) {
//                    layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
//                    adapter = adventureAdapter
//
//                    setHasFixedSize(true)
//                }
//                binding.groupTitle.setOnClickListener {
//                    val i = Intent(itemView.context, SeeAllActivity::class.java)
//                    val seeAllData = ItemAll(book.items)
//                    i.putExtra(SeeAllActivity.EXTRAS_ADVENTURE, seeAllData)
//                    itemView.context.startActivity(i)
//                }
//            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val itemBinding = RecyclerGroupBooksBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ParentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val data = this.bookList[position]

        if (getItemViewType(position) == 0) {
            holder.title.text = "Where there is love, there is life"
            val romanceAdapter = RomanceAdapter()
            romanceAdapter.setData(data.items)
            with(holder.rv) {
                layoutManager = LinearLayoutManager(holder.itemView.context,
                        LinearLayoutManager.HORIZONTAL, false)
                adapter = romanceAdapter
                setHasFixedSize(true)
            }
            holder.groupItem.setOnClickListener {
                val i = Intent(holder.itemView.context, SeeAllActivity::class.java)
                val seeAllData = ItemAll(data.items)
                i.putExtra(SeeAllActivity.EXTRAS_ROMANCE, seeAllData)
                holder.itemView.context.startActivity(i)
            }
        }
        else if (getItemViewType(position) == 1) {
            holder.title.text = "The journey, is the destination"
            val adventureAdapter = AdventureAdapter()
            adventureAdapter.setData(data.items)
            with(holder.rv) {
                layoutManager = LinearLayoutManager(holder.itemView.context,
                        LinearLayoutManager.HORIZONTAL, false)
                adapter = adventureAdapter

                setHasFixedSize(true)
            }
            holder.groupItem.setOnClickListener {
                val i = Intent(holder.itemView.context, SeeAllActivity::class.java)
                val seeAllData = ItemAll(data.items)
                i.putExtra(SeeAllActivity.EXTRAS_ADVENTURE, seeAllData)
                holder.itemView.context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.bookList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position % 5
    }

    fun setData(listData: List<BooksResponse>) {
        this.bookList.clear()
        this.bookList.addAll(listData)
        notifyDataSetChanged()
    }


}