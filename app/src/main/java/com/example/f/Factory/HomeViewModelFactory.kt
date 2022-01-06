package com.example.f.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.f.Api.BookHelper
import com.example.f.Repository.HomeRepository
import com.example.f.ViewModel.HomeViewModel
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val bookHelper: BookHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(HomeRepository(bookHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}