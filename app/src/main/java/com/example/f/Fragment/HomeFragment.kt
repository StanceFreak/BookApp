 package com.example.f.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.Adapter.HomeAdapter
import com.example.f.Api.BookClient
import com.example.f.Api.BookHelper
import com.example.f.Factory.HomeViewModelFactory
import com.example.f.R
import com.example.f.Utils.Status
import com.example.f.ViewModel.HomeViewModel
import com.example.f.databinding.FragmentHomeBinding

 class HomeFragment : Fragment(){

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

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

        setupRecycler()
        setupToolbar()
        setupObserver()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val search = menu.findItem(R.id.tb_search)
        val searchView = search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Looking for something?"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(context, "Query says $newText", Toast.LENGTH_SHORT).show()
                Log.d("textChange", "onQueryTextChange $newText")
                homeAdapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

     private fun setupRecycler() {
         homeAdapter = HomeAdapter()

         binding.homeRvRomance.apply {
             layoutManager = GridLayoutManager(requireActivity(), 2)
             adapter = homeAdapter
             setHasFixedSize(true)
         }

     }

     private fun setupToolbar() {
         binding.homeToolbar.inflateMenu(R.menu.menu_home)
         binding.homeToolbar.setOnMenuItemClickListener {
             when (it.itemId) {
                 R.id.tb_search -> Toast.makeText(
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

         viewModel = ViewModelProviders.of(
             this,
             HomeViewModelFactory(BookHelper(BookClient.instance))
         ).get(HomeViewModel::class.java)

         viewModel.getRomanceBooks(
             0,
             40
         ).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.homeRvRomance.visibility = View.VISIBLE
                        resource.data?.let { response ->
                            homeAdapter.setData(response.items)
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