package com.example.itunes.database.repository

import android.app.Application
import com.example.itunes.data.model.Result
import com.example.itunes.data.network.RetrofitInstance
import com.example.itunes.database.dao.SongsDao
import com.example.itunes.database.db.SongDatabase

class SongRepository(application: Application) {
    private val songsDao: SongsDao

    init {
        val db = SongDatabase.getDatabase(application)
        songsDao = db.songsDao()
    }

    suspend fun insertAllSongs(result: List<Result>) = songsDao.insertAllSongs(result)

    fun getAllSongs() = songsDao.getAllSongs()

    // Api call
    suspend fun makeApiCall(search: String) = RetrofitInstance.api.getAllSongs(term = search).body()

}