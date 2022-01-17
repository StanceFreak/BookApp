package com.example.f.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.f.Adapter.FavoriteAdapter
import com.example.f.R
import com.example.f.ViewModel.BookFavViewModel
import com.example.f.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: BookFavViewModel
    private lateinit var favAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentFavoriteBinding.inflate(inflater, container, false)

        setupRecycler()

        return binding.root
    }

    private fun setupRecycler() {
        favAdapter = FavoriteAdapter()

        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = favAdapter
            setHasFixedSize(true)
        }

        viewModel = ViewModelProvider(this).get(BookFavViewModel::class.java)
        viewModel.getFavorite.observe(viewLifecycleOwner, Observer {
            favAdapter.setFavorite(it)
        })
    }

}