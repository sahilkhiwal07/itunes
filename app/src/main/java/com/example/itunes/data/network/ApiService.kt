package com.example.itunes.data.network

import com.example.itunes.data.model.Songs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getAllSongs (
        @Query("term") term: String
    ): Response<Songs>

}