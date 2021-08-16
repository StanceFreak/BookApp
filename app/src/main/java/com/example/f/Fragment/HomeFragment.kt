package com.example.f.Fragment

//import com.example.f.Api.ApiRepository
//import com.example.f.Factory.HomeVMFactory
//import com.example.f.ViewModel.HomeViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Adapter.HomeAdapter
import com.example.f.Api.BookClient
import com.example.f.Factory.HomeFactory
import com.example.f.Model.Books
import com.example.f.Model.Item
import com.example.f.R
import com.example.f.Repository.BookRepository
import com.example.f.ViewModel.BookViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

//    private lateinit var viewModel: BookViewModel
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var homeAdapter: HomeAdapter
    lateinit var viewModel: BookViewModel
    private var list = ArrayList<Item>()

    private val TAG: String = "Test"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val repository = BookRepository()
//        val factory = HomeFactory(repository)
//        viewModel = ViewModelProvider(this, factory).get(BookViewModel::class.java)
//        viewModel.getBooks()
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
//            homeAdapter.setData(response)
//        })

        setupRecyclerView()
        getDataApi()
    }



    private fun getDataApi() {
        BookClient.instance.getBooks()
                .enqueue(object : Callback<Books> {
                    override fun onResponse(call: Call<Books>, response: Response<Books>) {
                        if(response.isSuccessful) {
                            val result = response.body()
//                            homeAdapter.setData(result)
                        }
//                        result?.items
                    }

                    override fun onFailure(call: Call<Books>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
//
                })
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        val popular = view?.findViewById<RecyclerView>(R.id.home_rv_popular)
        with(popular) {
            linearLayoutManager = LinearLayoutManager(requireContext())
            this!!.adapter = homeAdapter
        }
    }

    private fun showMsg(message: String) {
        Log.d(TAG, message)
    }
}