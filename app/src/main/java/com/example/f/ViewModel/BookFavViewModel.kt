package com.example.f.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.f.Model.Local.BookFavDatabase
import com.example.f.Model.Local.BookFavEntity
import com.example.f.Repository.BookFavRepository
import kotlinx.coroutines.launch

class BookFavViewModel(application: Application) : AndroidViewModel(application) {

    private val bookFavRepository: BookFavRepository
    val getFavorite : LiveData<List<BookFavEntity>>

    init {
        val bookHelper = BookFavDatabase.getDatabase(application).bookFavDao()
        bookFavRepository = BookFavRepository(bookHelper)
        getFavorite = bookFavRepository.getFavorite()
    }

    fun addFav(bookFavEntity: BookFavEntity) {
        viewModelScope.launch {
            bookFavRepository.addFav(bookFavEntity)
        }
    }

    fun deleteFav(id: String) {
        viewModelScope.launch {
            bookFavRepository.deleteFav(id)
        }
    }

}