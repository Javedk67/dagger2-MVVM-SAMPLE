package com.example.dagger2sample.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ListResponse(
    val Search: List<MovieBean>,
    val totalResults: Int,
    val Response: String
)
@Parcelize
data class MovieBean(
    var Title: String,
    var Year: String,
    var imdbID: String,
    var Type: String,
    var Poster: String
) : Parcelable