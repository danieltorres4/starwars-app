package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("homeworld")
    var homeWorld: String? = null,

    @SerializedName("films")
    var films: ArrayList<Movie>
)
