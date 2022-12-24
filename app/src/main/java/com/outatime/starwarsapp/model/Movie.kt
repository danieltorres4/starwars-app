package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("count")
    var count: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("image")
    var image: String? = null
)
