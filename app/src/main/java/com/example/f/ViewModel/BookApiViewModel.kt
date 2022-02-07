package com.example.f.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.f.Repository.BookApiRepository
import com.example.f.Utils.Resource
import kotlinx.coroutines.Dispatchers

class BookApiViewModel(private val apiRepository: BookApiRepository): ViewModel() {

    fun getRomanceBooks(
        startIndex : Int,
        maxResults : Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getRomanceBooks(startIndex, maxResults)))
        }
        catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }

    }

    fun getAdventureBooks(
            startIndex : Int,
            maxResults : Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getAdventureBooks(startIndex, maxResults)))
        }
        catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }

    }

    fun getGeorgeMartinBooks(
            startIndex : Int,
            maxResults : Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getGeorgeMartinBooks(startIndex, maxResults)))
        }
        catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }

    }

}