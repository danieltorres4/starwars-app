package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class Planet(
    val count: Int,
    val results: ArrayList<Results>
)

data class Results(
    val name: String
)