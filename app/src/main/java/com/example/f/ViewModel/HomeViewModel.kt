package com.example.f.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.f.Repository.HomeRepository
import com.example.f.Utils.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    fun getRomanceBooks(
        startIndex : Int,
        maxResults : Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getRomanceBooks(startIndex, maxResults)))
        }
        catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }

    }

}