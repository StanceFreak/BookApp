package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.SeeAllActivity
import com.example.f.Model.ItemAll
import com.example.f.Model.Model.BooksResponse
import com.example.f.databinding.RecyclerGroupBooksBinding

class GroupAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList = ArrayList<BooksResponse>()

    companion object{
        const val ROMANCE = 0
        const val ADVENTURE = 1
    }


    class AdventureViewHolder(binding: RecyclerGroupBooksBinding) : RecyclerView.ViewHolder(
            binding.root
    ) {

        val title = binding.title
        val rv = binding.rvChild
        val groupItem = binding.groupItem

    }

    class RomanceViewHolder(binding: RecyclerGroupBooksBinding) : RecyclerView.ViewHolder(
            binding.root
    ) {

        val title = binding.title
        val rv = binding.rvChild
        val groupItem = binding.groupItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ADVENTURE) {
            return AdventureViewHolder(
                RecyclerGroupBooksBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        if (viewType == ROMANCE) {
            return RomanceViewHolder(
                RecyclerGroupBooksBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = this.bookList[position]

        if (data.position == ROMANCE) {
            val romanceHolder = holder as RomanceViewHolder
            romanceHolder.title.text = data.title
            val romanceAdapter = RomanceAdapter()
            romanceAdapter.setData(data.items)
            with(romanceHolder.rv) {
                layoutManager = LinearLayoutManager(
                    romanceHolder.itemView.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                adapter = romanceAdapter
                setHasFixedSize(true)
            }
            romanceHolder.groupItem.setOnClickListener {
                val i = Intent(romanceHolder.itemView.context, SeeAllActivity::class.java)
                val seeAllData = ItemAll(data.items)
                i.putExtra(SeeAllActivity.EXTRAS_ROMANCE, seeAllData)
                romanceHolder.itemView.context.startActivity(i)
            }
        }

        if (data.position == ADVENTURE) {
            val adventureHolder = holder as AdventureViewHolder
            adventureHolder.title.text = data.title
            val adventureAdapter = AdventureAdapter()
            adventureAdapter.setData(data.items)
            with(adventureHolder.rv) {
                layoutManager = LinearLayoutManager(
                    adventureHolder.itemView.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                adapter = adventureAdapter
                setHasFixedSize(true)
            }
            adventureHolder.groupItem.setOnClickListener {
                val i = Intent(adventureHolder.itemView.context, SeeAllActivity::class.java)
                val seeAllData = ItemAll(data.items)
                i.putExtra(SeeAllActivity.EXTRAS_ADVENTURE, seeAllData)
                adventureHolder.itemView.context.startActivity(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.bookList.size
    }

    override fun getItemViewType(position: Int): Int {
        return this.bookList[position].position
    }

    fun setData(listData: List<BooksResponse>) {
        this.bookList = listData as ArrayList
        notifyDataSetChanged()
    }

}