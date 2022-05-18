 package com.example.f.Fragment

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Context.SEARCH_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f.Activity.LandingActivity
import com.example.f.Activity.SearchResultActivty
import com.example.f.Adapter.AdapterGroupItem
import com.example.f.Api.BookApiClient
import com.example.f.Api.BookApiHelper
import com.example.f.Factory.BookViewModelFactory
import com.example.f.Model.BookModel.BooksResponse
import com.example.f.R
import com.example.f.Utils.Status
import com.example.f.ViewModel.BookApiViewModel
import com.example.f.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

 class HomeFragment : Fragment(){

    private lateinit var groupAdapter: AdapterGroupItem
    private lateinit var binding: FragmentHomeBinding
    private lateinit var apiViewModel: BookApiViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupToolbar()
        setupObserver()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val search = menu.findItem(R.id.search_home)
        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = search?.actionView as SearchView
        searchView.apply {
            queryHint = "Looking for something?"
            isSubmitButtonEnabled = true
            setSearchableInfo(searchManager.getSearchableInfo(ComponentName(
                    requireContext(),
                    SearchResultActivty::class.java
            )))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        val i = Intent(activity, SearchResultActivty::class.java)
                        i.putExtra(SearchResultActivty.QUERY_EXTRAS, query)
                        startActivity(i)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
//                groupAdapter.getFilter().filter(newText)
//                val e : Exception = Exception()
//                e.printStackTrace()
                    return false
                }

            })
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

     private fun setupRecycler() {
         groupAdapter = AdapterGroupItem()
         binding.rvHome.apply {
             layoutManager = LinearLayoutManager(
                     requireActivity(),
                     LinearLayoutManager.VERTICAL,
                     false
             )
             adapter = groupAdapter
             setHasFixedSize(true)
         }
     }

     private fun setupToolbar() {
         binding.homeToolbar.inflateMenu(R.menu.menu_home)
         binding.homeToolbar.setOnMenuItemClickListener {
             when (it.itemId) {
                 R.id.search_home -> Toast.makeText(
                         context,
                         "Clicked search button",
                         Toast.LENGTH_SHORT
                 )
                         .show()
             }
             return@setOnMenuItemClickListener true
         }
     }

     private fun setupObserver() {

         val BooksData = ArrayList<BooksResponse>()

         val queryMap = HashMap<String, Any>()
         queryMap["startIndex"] = 0
         queryMap["maxResults"] = 40

         apiViewModel = ViewModelProviders.of(
                 this,
                 BookViewModelFactory(BookApiHelper(BookApiClient.instance))
         ).get(BookApiViewModel::class.java)

         apiViewModel.getBooksByGenre(
                "subject:romance",
                queryMap
             ).observe(viewLifecycleOwner, {
                 it?.let { resource ->
                     when (resource.status) {
                         Status.SUCCESS -> {
                             binding.pbHome.visibility = View.GONE
                             binding.rvHome.visibility = View.VISIBLE
                             resource.data?.let { response ->
                                 BooksData.add(BooksResponse(
                                         AdapterGroupItem.ROMANCE,
                                         "Heartwarming love story",
                                         response.items
                                 ))
                                 groupAdapter.setData(BooksData)
                                 val e = Exception()
                                 e.printStackTrace()
                             }
                         }
                         Status.ERROR -> {
                             binding.pbHome.visibility = View.INVISIBLE
                             Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                         }
                         Status.LOADING -> {
                             binding.pbHome.visibility = View.VISIBLE
                         }
                     }
                 }
             })

             apiViewModel.getBooksByGenre(
                     "subject:adventure",
                     queryMap
             ).observe(viewLifecycleOwner, {
                 it?.let { resource ->
                     when (resource.status) {
                         Status.SUCCESS -> {
                             binding.pbHome.visibility = View.GONE
                             binding.rvHome.visibility = View.VISIBLE
                             resource.data?.let { response ->
                                 BooksData.add(BooksResponse(
                                         AdapterGroupItem.ADVENTURE,
                                         "Thrilling & exciting adventures",
                                         response.items
                                 ))
                                 groupAdapter.setData(BooksData)
                                 val e = Exception()
                                 e.printStackTrace()
                             }
                         }
                         Status.ERROR -> {
                             binding.pbHome.visibility = View.INVISIBLE
                             Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                         }
                         Status.LOADING -> {
                             binding.pbHome.visibility = View.VISIBLE
                         }
                     }
                 }
             })

         apiViewModel.getGeorgeMartinBooks(
                 0,
                 40
         ).observe(viewLifecycleOwner, {
             it?.let { resource ->
                 when (resource.status) {
                     Status.SUCCESS -> {
                         binding.pbHome.visibility = View.GONE
                         binding.rvHome.visibility = View.VISIBLE
                         resource.data?.let { response ->
                             BooksData.add(BooksResponse(
                                     AdapterGroupItem.GEORGE,
                                     "Written by George R.R. Martin",
                                     response.items
                             ))
                             groupAdapter.setData(BooksData)
                             val e = Exception()
                             e.printStackTrace()
                         }
                     }
                     Status.ERROR -> {
                         binding.pbHome.visibility = View.INVISIBLE
                         Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                     }
                     Status.LOADING -> {
                         binding.pbHome.visibility = View.VISIBLE
                     }
                 }
             }
         })

     }
}