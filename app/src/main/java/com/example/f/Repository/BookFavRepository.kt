package com.example.f.Repository

import androidx.lifecycle.LiveData
import com.example.f.Local.BookFavDao
import com.example.f.Local.BookFavEntity


class BookFavRepository(private val bookFavDao: BookFavDao) {

    fun getFavorite() : LiveData<List<BookFavEntity>> = bookFavDao.getFavorite()

    suspend fun addFav(bookFavEntity: BookFavEntity) {
        bookFavDao.addFav(bookFavEntity)
    }

    suspend fun deleteFav(id: Int) {
        bookFavDao.deleteFav(id)
    }

//    suspend fun isFavorite(id : Int) {
//        bookFavDao.isFavorite(id)
//    }

}