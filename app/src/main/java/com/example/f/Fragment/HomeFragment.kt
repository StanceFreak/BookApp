 package com.example.f.Fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f.Adapter.GroupAdapter
import com.example.f.Api.BookApiClient
import com.example.f.Api.BookApiHelper
import com.example.f.Factory.BookViewModelFactory
import com.example.f.R
import com.example.f.Utils.Status
import com.example.f.ViewModel.BookApiViewModel
import com.example.f.databinding.FragmentHomeBinding

 class HomeFragment : Fragment(){

    private lateinit var groupAdapter: GroupAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var apiViewModel: BookApiViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val title = GroupTitle.title
//        val data = ArrayList<Type>()
//
//        for (t in title.indices) {
//            val groupTitle = Type(title[t])
//            data.add(groupTitle)
//        }

        setupRecycler()
        setupToolbar()
        setupObserver()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val search = menu.findItem(R.id.search_home)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Looking for something?"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                romanceAdapter.filter.filter(newText)
//                val e : Exception = Exception()
//                e.printStackTrace()
//                return true
//            }
//        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

     private fun setupRecycler() {

         groupAdapter = GroupAdapter()

         binding.rvHome.apply {
             layoutManager = LinearLayoutManager(
                     requireActivity(),
                     LinearLayoutManager.HORIZONTAL,
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

         apiViewModel = ViewModelProviders.of(
                 this,
                 BookViewModelFactory(BookApiHelper(BookApiClient.instance))
         ).get(BookApiViewModel::class.java)

         apiViewModel.apply {
             getRomanceBooks(
                     0,
                     40
             ).observe(viewLifecycleOwner, {
                 it?.let { resource ->
                     when (resource.status) {
                         Status.SUCCESS -> {
                             binding.progressBar.visibility = View.GONE
                             binding.rvHome.visibility = View.VISIBLE
                             resource.data?.let { response ->
                                 groupAdapter.setData(response)
                             }
                         }
                         Status.ERROR -> {
                             binding.progressBar.visibility = View.INVISIBLE
                             Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                         }
                         Status.LOADING -> {
                             binding.progressBar.visibility = View.VISIBLE
                         }
                     }
                 }
             })

             getAdventureBooks(
                     0,
                     40
             ).observe(viewLifecycleOwner, {
                 it?.let { resource ->
                     when (resource.status) {
                         Status.SUCCESS -> {
                             binding.progressBar.visibility = View.GONE
                             binding.rvHome.visibility = View.VISIBLE
                             resource.data?.let { response ->
                                 groupAdapter.setData(response)
                             }
                         }
                         Status.ERROR -> {
                             binding.progressBar.visibility = View.INVISIBLE
                             Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                         }
                         Status.LOADING -> {
                             binding.progressBar.visibility = View.VISIBLE
                         }
                     }
                 }
             })
         }
     }
}