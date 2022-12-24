package com.outatime.starwarsapp.model

import com.google.gson.annotations.SerializedName

data class People(
    val count: Int,
    val results: ArrayList<Result>
)

class Result(
    val birth_year: String,
    val gender: String,
    val height: String,
    val name: String
)