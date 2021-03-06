package com.example.itunes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.itunes.data.model.Result

@Dao
interface SongsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSongsByArtistName(result: List<Result>)

    @Query("select * from Result where artistName LIKE :name")
    fun getSongsByArtist(name: String): LiveData<List<Result>>

    @Query("select * from Result")
    fun getAllSongs(): LiveData<List<Result>>

}