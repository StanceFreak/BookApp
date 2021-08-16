package com.example.f.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f.Model.BookItem
import com.example.f.Model.Books
import com.example.f.Repository.BookRepository
import kotlinx.coroutines.launch
import retrofit2.Call

class BookViewModel(private val repository: BookRepository): ViewModel() {

    val myResponse : MutableLiveData<Call<Books>> = MutableLiveData()

    fun getBooks() {
        viewModelScope.launch {
            val response = repository.getBooks()
            myResponse.value = response
        }
    }
}