package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val count: Int,
    val results: ArrayList<MovieResults>
)

data class MovieResults(
    val episode_id: Int,
    val title: String,
    val director: String,
    val release_date: String
)