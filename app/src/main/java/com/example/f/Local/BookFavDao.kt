package com.example.f.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookFavDao {

    @Insert
    suspend fun addFav (bookFavEntity: BookFavEntity)

    @Query("DELETE FROM favorite_table where id = :id")
    suspend fun deleteFav (id: Int)

    @Query("SELECT * FROM favorite_table")
    fun getFavorite() : LiveData<List<BookFavEntity>>

//    @Query("SELECT EXISTS (SELECT 1 FROM favorite_table WHERE id = :id)")
//    suspend fun isFavorite(id : Int)

}