package com.example.f.Repository

import androidx.lifecycle.LiveData
import com.example.f.Model.Local.BookFavDao
import com.example.f.Model.Local.BookFavEntity


class BookFavRepository(private val bookFavDao: BookFavDao) {

    fun getFavorite() : LiveData<List<BookFavEntity>> = bookFavDao.getFavorite()

    suspend fun addFav(bookFavEntity: BookFavEntity) {
        bookFavDao.addFav(bookFavEntity)
    }

    suspend fun deleteFav(id: String) {
        bookFavDao.deleteFav(id)
    }

}