package com.example.f.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.f.Adapter.AdapterActivitySeeAll
import com.example.f.Api.BookApiClient
import com.example.f.Api.BookApiHelper
import com.example.f.Factory.BookViewModelFactory
import com.example.f.R
import com.example.f.Utils.Status
import com.example.f.ViewModel.BookApiViewModel
import com.example.f.databinding.ActivitySeeAllBinding

class SeeAllActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeeAllBinding
    private lateinit var viewModel: BookApiViewModel
    private lateinit var allAdapter: AdapterActivitySeeAll

    companion object {
        const val BOOK_TYPE = "book_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeAllBinding.inflate(layoutInflater)

        setToolbar()
        setupRecycler()
        setupData()

        setContentView(binding.root)
    }

    private fun setToolbar() {
        binding.seeAllToolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
        }
        setSupportActionBar(binding.seeAllToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.seeAllToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupRecycler() {
        allAdapter = AdapterActivitySeeAll()
        with(binding.seeAllRv) {
            layoutManager = GridLayoutManager(this@SeeAllActivity, 2)
            setHasFixedSize(true)
            adapter = allAdapter
        }

    }

    private fun setupData() {
        val data = intent.getStringExtra(BOOK_TYPE)

        binding.seeAllTitle.text = data

        viewModel = ViewModelProviders.of(
                this,
                BookViewModelFactory(BookApiHelper(BookApiClient.instance))
        ).get(BookApiViewModel::class.java)

        val queryMap = HashMap<String, Any>()
        queryMap["startIndex"] = 0
        queryMap["maxResults"] = 40

        if (data.equals("Heartwarming love story")) {
            viewModel.getBooksByGenre(
                    "subject:romance",
                    queryMap
            ).observe(this, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.seeAllLoading.visibility = View.GONE
                            binding.seeAllRv.visibility = View.VISIBLE
                            resource.data?.let { response ->
                                allAdapter.setData(response.items)
                                val e = Exception()
                                e.printStackTrace()
                            }
                        }
                        Status.ERROR -> {
                            binding.seeAllLoading.visibility = View.INVISIBLE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.seeAllLoading.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

        if (data.equals("Thrilling & exciting adventures")) {
            viewModel.getBooksByGenre(
                    "subject:adventure",
                    queryMap
            ).observe(this, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.seeAllLoading.visibility = View.GONE
                            binding.seeAllRv.visibility = View.VISIBLE
                            resource.data?.let { response ->
                                allAdapter.setData(response.items)
                                val e = Exception()
                                e.printStackTrace()
                            }
                        }
                        Status.ERROR -> {
                            binding.seeAllLoading.visibility = View.INVISIBLE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.seeAllLoading.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

        if (data.equals("Written by George R.R. Martin")) {
            viewModel.getGeorgeMartinBooks(
                    0,
                    40
            ).observe(this, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.seeAllLoading.visibility = View.GONE
                            binding.seeAllRv.visibility = View.VISIBLE
                            resource.data?.let { response ->
                                allAdapter.setData(response.items)
                                val e = Exception()
                                e.printStackTrace()
                            }
                        }
                        Status.ERROR -> {
                            binding.seeAllLoading.visibility = View.INVISIBLE
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            binding.seeAllLoading.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

    }

}