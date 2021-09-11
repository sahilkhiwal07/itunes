package com.example.itunes.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.*
import com.example.itunes.utils.ItunesApplication
import com.example.itunes.data.model.Result
import com.example.itunes.database.repository.SongRepository
import kotlinx.coroutines.launch

class SongsViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val repository = SongRepository(application)
    private val trackLiveData: MutableLiveData<List<Result>> = MutableLiveData()
    private val nameLiveData = MutableLiveData<String>()

    val filterSongsLiveData = Transformations.switchMap(nameLiveData) {
        repository.getSongsByArtistName(it)
    }

    fun getSongsByArtistName(name: String) {
        nameLiveData.postValue(name)
    }

    private fun insertSongsByArtistName(result: List<Result>) {
        viewModelScope.launch {
            repository.insertSongsByArtistName(result)
        }
    }

    fun getAllSongs(): LiveData<List<Result>> {
        return repository.getAllSongs()
    }

    fun getTracks():LiveData<List<Result>>  {
        return trackLiveData
    }

    fun makeApiCallForSong(search: String) = viewModelScope.launch {
        val songsResponse = repository.makeApiCall(search)
        if (songsResponse != null) {
            this@SongsViewModel.insertSongsByArtistName(songsResponse.results)
            this@SongsViewModel.trackLiveData.postValue(songsResponse.results)
        }
    }

    // Network Response

    fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<ItunesApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}