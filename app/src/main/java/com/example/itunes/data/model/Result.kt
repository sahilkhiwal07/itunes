package com.example.itunes.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Result(
    @PrimaryKey(autoGenerate = true)
    var artistId: Int? = null,
    val artistName: String? = null,
    val artworkUrl100: String? = null,
    val collectionName: String? = null,
    val releaseDate: String? = null,
    val trackName: String? = null,
    val trackNumber: Int? = null
): Parcelable