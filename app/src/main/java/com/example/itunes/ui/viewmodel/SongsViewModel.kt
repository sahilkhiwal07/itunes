package com.example.itunes.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.itunes.data.model.Result
import com.example.itunes.data.network.RetrofitInstance
import com.example.itunes.database.repository.SongRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongsViewModel constructor(application: Application): AndroidViewModel(application) {

    private val repository = SongRepository(application)
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private fun insertAllSongs(result: List<Result>){
        viewModelScope.launch {
            repository.insertAllSongs(result)
        }
    }

    fun getAllSongs(): LiveData<List<Result>> {
        return repository.getAllSongs()
    }

    fun makeApiCall(search: String) {
        viewModelScope.launch(ioDispatcher) {
            val songs = RetrofitInstance.api.getAllSongs(search).body()
            if (songs != null) {
                this@SongsViewModel.insertAllSongs(songs.results)
            }
        }
    }

}