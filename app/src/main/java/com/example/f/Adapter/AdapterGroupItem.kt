package com.example.f.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Activity.SeeAllActivity
import com.example.f.Model.BookModel.BooksResponse
import com.example.f.databinding.RecyclerGroupBooksBinding
import kotlin.collections.ArrayList

class AdapterGroupItem : RecyclerView.Adapter<AdapterGroupItem.ViewHolder>() {

    private var bookList = ArrayList<BooksResponse>()
    private var filteredBookList = ArrayList<BooksResponse>()

    companion object{
        const val ROMANCE = 0
        const val ADVENTURE = 1
        const val GEORGE = 2
    }

    class ViewHolder(binding: RecyclerGroupBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val rv = binding.rvChild
        val groupItem = binding.groupItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == ROMANCE || viewType == ADVENTURE || viewType == GEORGE) {
            return ViewHolder(
                RecyclerGroupBooksBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
            )
        }

        else {
            throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = this.bookList[position]

        if (data.position == ROMANCE) {
            holder.title.text = data.title
            val romanceAdapter = AdapterItemRomance()
            romanceAdapter.setData(data.items)
            with(holder.rv) {
                layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                adapter = romanceAdapter
                setHasFixedSize(true)
            }
            holder.groupItem.setOnClickListener {
                val i = Intent(holder.itemView.context, SeeAllActivity::class.java)
                i.putExtra(SeeAllActivity.BOOK_TYPE, "Heartwarming love story")
                holder.itemView.context.startActivity(i)
            }
        }

        if (data.position == ADVENTURE) {
            holder.title.text = data.title
            val adventureAdapter = AdapterItemAdventure()
            adventureAdapter.setData(data.items)
            with(holder.rv) {
                layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                adapter = adventureAdapter
                setHasFixedSize(true)
            }
            holder.groupItem.setOnClickListener {
                val i = Intent(holder.itemView.context, SeeAllActivity::class.java)
                i.putExtra(SeeAllActivity.BOOK_TYPE, "Thrilling & exciting adventures")
                holder.itemView.context.startActivity(i)
            }
        }

        if (data.position == GEORGE) {
            holder.title.text = data.title
            val georgeAdapter = AdapterItemGeorge()
            georgeAdapter.setData(data.items)
            with(holder.rv) {
                layoutManager = LinearLayoutManager(
                        holder.itemView.context,
                        LinearLayoutManager.HORIZONTAL, false
                )
                adapter = georgeAdapter
                setHasFixedSize(true)
            }
            holder.groupItem.setOnClickListener {
                val i = Intent(holder.itemView.context, SeeAllActivity::class.java)
                i.putExtra(SeeAllActivity.BOOK_TYPE, "Written by George R.R. Martin")
                holder.itemView.context.startActivity(i)
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
        this.bookList.clear()
        this.bookList.addAll(listData)
        this.filteredBookList.clear()
        this.filteredBookList.addAll(listData)
        notifyDataSetChanged()
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence?): FilterResults {
//                val queryString = charSequence.toString()
//
//                if (queryString.isEmpty()) {
//                    filteredBookList = bookList
//                }
//                else {
//                    val tempFilteredData = ArrayList<BooksResponse>()
//
//                    for (item in bookList) {
//                        if (it.toLowerCase(Locale.ROOT)!!
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