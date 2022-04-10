package com.example.f.Model.Local

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class BookFavEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,

    @ColumnInfo(name = "bookId")
    val bookId: String,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "averageRating")
    val averageRating: Float?,

    @ColumnInfo(name = "ratingCount")
    val ratingCount: Int?,

    @ColumnInfo(name = "desc")
    val desc: String?,

    @ColumnInfo(name = "pageCount")
    val pageCount: Int?,

    @ColumnInfo(name = "amountInMicros")
    val amountInMicros: Long?,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String?,

    @ColumnInfo(name = "previewLink")
    val previewLink: String?

): Parcelable
