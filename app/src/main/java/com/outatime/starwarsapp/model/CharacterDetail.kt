package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("height")
    var height: String? = null,

    @SerializedName("birth_year")
    var birthday: String? = null,

    @SerializedName("gender")
    var gender: String? = null
)
