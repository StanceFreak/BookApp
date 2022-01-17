package com.example.f.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BookFavEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BookFavDatabase : RoomDatabase() {

    abstract fun bookFavDao(): BookFavDao

    companion object{
        @Volatile
        private var INSTANCE: BookFavDatabase? = null

        fun getDatabase(context: Context): BookFavDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookFavDatabase::class.java,
                    "favorite_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}