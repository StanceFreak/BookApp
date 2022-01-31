package com.example.f.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav (bookFavEntity: BookFavEntity)

    @Query("DELETE FROM favorite_table WHERE favorite_table.bookId = :id")
    suspend fun deleteFav (id: String): Int

    @Query("SELECT * FROM favorite_table")
    fun getFavorite() : LiveData<List<BookFavEntity>>

}