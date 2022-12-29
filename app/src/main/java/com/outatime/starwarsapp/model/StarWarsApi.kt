package com.outatime.starwarsapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApi {
    //END POINTS
    @GET("api/people/")
    fun getCharacters(): Call<People>

    @GET("api/people/{count}/")
    fun getCharacterDetail(@Path("count") count: Int?): Call<CharacterDetail>

    @GET("api/planets/")
    fun getPlanets(): Call<Planet>

    @GET("api/planets/{planetCount}")
    fun getPlanetDetail(@Path("planetCount") count: Int?): Call<PlanetDetails>

    @GET("api/films/")
    fun getFilms(): Call<Movie>

    @GET("api/films/{filmCount}")
    fun getFilmDetails(@Path("filmCount") filmCount: String?): Call<MovieDetail>

    @GET("cm2023-1/{filmId}")
    fun getFilmImg(@Path("filmId") filmId: String?): Call<MovieImage>
}