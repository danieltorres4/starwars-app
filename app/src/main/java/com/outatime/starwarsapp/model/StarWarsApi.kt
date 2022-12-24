package com.outatime.starwarsapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApi {
    //END POINTS
    @GET
    fun getCharacters(@Url url: String?): Call<ArrayList<Character>>

    @GET("api/people/{count}")
    fun getCharacterDetail(@Path("count") count: Int?): Call<CharacterDetail>
}