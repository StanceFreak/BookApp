package com.example.f.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.f.Api.BookApiHelper
import com.example.f.Repository.BookApiRepository
import com.example.f.ViewModel.BookApiViewModel
import java.lang.IllegalArgumentException

class BookViewModelFactory(private val bookApiHelper: BookApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookApiViewModel::class.java)) {
            return BookApiViewModel(BookApiRepository(bookApiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}