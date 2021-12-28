 package com.example.f.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Adapter.HomeAdapter
import com.example.f.Api.BookClient
import com.example.f.ModelRomance.Item
import com.example.f.ModelRomance.RomanceBooks
import com.example.f.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class HomeFragment : Fragment(){

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.home_toolbar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.home_rv_romance)
        homeAdapter = HomeAdapter()

        recyclerView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = homeAdapter
            setHasFixedSize(true)
        }

        toolbar.inflateMenu(R.menu.menu_home)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.tb_search -> Toast.makeText(context,
                        "Clicked search button",
                        Toast.LENGTH_SHORT)
                        .show()
            }
            return@setOnMenuItemClickListener true
        }



        getDataApi()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val search = menu.findItem(R.id.tb_search)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("textChange", "newText : " + newText)
                homeAdapter.filter.filter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }



     private fun getDataApi() {
        val callRomance: Call<RomanceBooks> = BookClient.instance.getRomanceBooks(
                0,
                40
        )


        callRomance.enqueue(object : Callback<RomanceBooks> {
            override fun onResponse(call: Call<RomanceBooks>, response: Response<RomanceBooks>) {

                if (response.isSuccessful) {
                    Log.d("test", response.body()!!.toString())
                    homeAdapter.setData(response.body()!!.items as ArrayList<Item>)
                }

            }

            override fun onFailure(call: Call<RomanceBooks>, t: Throwable) {
                Toast.makeText(requireContext(),
                        "Check your network connection",
                        Toast.LENGTH_LONG)
                        .show()
                Log.e("error", "OnFailure : " + t.message)
            }

        })
    }




}