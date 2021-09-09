package com.example.itunes.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.itunes.data.model.Result
import com.example.itunes.data.network.RetrofitInstance
import com.example.itunes.database.repository.SongRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class SongsViewModel constructor(application: Application): AndroidViewModel(application) {

    private val repository = SongRepository(application)

    private fun insertAllSongs(result: List<Result>){
        viewModelScope.launch {
            repository.insertAllSongs(result)
        }
    }

    fun getAllSongs(): LiveData<List<Result>> {
        return repository.getAllSongs()
    }

    fun makeApiCallForSong(search: String) = viewModelScope.launch {
        val songsResponse = repository.makeApiCall(search)
        try {
            if (songsResponse != null) {
                this@SongsViewModel.insertAllSongs(songsResponse.results)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}