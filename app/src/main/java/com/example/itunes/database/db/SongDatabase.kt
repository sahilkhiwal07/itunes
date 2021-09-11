package com.example.itunes.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunes.data.model.Result
import com.example.itunes.database.dao.SongsDao

@Database(
    entities = [Result::class],
    version = 1
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songsDao(): SongsDao

    companion object {

        @Volatile
        private var INSTANCE: SongDatabase? = null

        fun getDatabase(context: Context): SongDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongDatabase::class.java,
                    "song_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}