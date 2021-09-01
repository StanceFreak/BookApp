package com.example.f.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Adapter.HomeAdapter
import com.example.f.Api.BookClient
import com.example.f.ModelRomance.RomanceBooks
import com.example.f.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.home_rv_romance)
        homeAdapter = HomeAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = homeAdapter
            setHasFixedSize(true)
        }
        getDataApi()
    }

    private fun getDataApi() {
        val call: Call<RomanceBooks> = BookClient.instance.getRomanceBooks()

        call.enqueue(object : Callback<RomanceBooks> {
            override fun onResponse(call: Call<RomanceBooks>, response: Response<RomanceBooks>) {

                if (response.isSuccessful) {
                    Log.d("test", response.body()!!.toString())
                    homeAdapter.setData(response.body()!!.items)
                }

            }

            override fun onFailure(call: Call<RomanceBooks>, t: Throwable) {
                Toast.makeText(requireContext(), "Check your network connection", Toast.LENGTH_LONG).show()
                Log.e("error", "OnFailure : " + t.message)
            }

        })
    }

    private fun setupRecycler() {

    }
}