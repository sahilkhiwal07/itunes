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
//    val artistViewUrl: String? = null,
    val artworkUrl100: String? = null,
//    val artworkUrl30: String? = null,
//    val artworkUrl60: String? = null,
//    val artworkUrl600: String? = null,
//    val collectionArtistId: Int? = null,
//    val collectionArtistName: String? = null,
//    val collectionArtistViewUrl: String? = null,
//    val collectionCensoredName: String? = null,
//    val collectionExplicitness: String? = null,
//    val collectionHdPrice: Int? = null,
//    val collectionId: Int? = null,
    val collectionName: String? = null,
//    val collectionPrice: Double? = null,
//    val collectionViewUrl: String? = null,
//    val contentAdvisoryRating: String? = null,
//    val country: String? = null,
//    val currency: String? = null,
//    val discCount: Int? = null,
//    val discNumber: Int? = null,
//    val feedUrl: String? = null,
//    val genreIds: List<String>,
//    val genres: List<String>,
//    val isStreamable: Boolean? = null,
//    val kind: String? = null,
//    val previewUrl: String? = null,
//    val primaryGenreName: String? = null,
//    val releaseDate: String? = null,
//    val trackCensoredName: String? = null,
//    val trackCount: Int? = null,
//    val trackExplicitness: String? = null,
//    val trackHdPrice: Int? = null,
//    val trackHdRentalPrice: Int? = null,
//    val trackId: Int? = null,
    val trackName: String? = null
//    val trackNumber: Int? = null,
//    val trackPrice: Double? = null,
//    val trackRentalPrice: Int? = null,
//    val trackTimeMillis: Int? = null,
//    val trackViewUrl: String? = null,
//    val wrapperType: String? = null
): Parcelable