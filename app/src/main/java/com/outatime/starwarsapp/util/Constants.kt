package com.outatime.starwarsapp.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    const val BASE_URL = "https://swapi.dev/"
    const val LOGTAG = "LOGS"

    val CHARACTERS: Map<String, Int> = mapOf(Pair("Luke Skywalker", 1),
                                            Pair("C-3PO", 2),
                                            Pair("R2-D2", 3),
                                            Pair("Darth Vader", 4),
                                            Pair("Leia Organa", 5),
                                            Pair("Owen Lars", 6),
                                            Pair("Beru Whitesun lars", 7),
                                            Pair("R5-D4", 8),
                                            Pair("Biggs Darklighter", 9),
                                            Pair("Obi-Wan Kenobi", 10))

    val PLANETS: Map<String, Int> = mapOf(Pair("Luke Skywalker", 1),
                                        Pair("C-3PO", 1),
                                        Pair("R2-D2", 8),
                                        Pair("Darth Vader", 1),
                                        Pair("Leia Organa", 2),
                                        Pair("Owen Lars", 1),
                                        Pair("Beru Whitesun lars", 1),
                                        Pair("R5-D4", 1),
                                        Pair("Biggs Darklighter", 1),
                                        Pair("Obi-Wan Kenobi", 20))

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}