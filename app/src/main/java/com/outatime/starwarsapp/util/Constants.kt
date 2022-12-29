package com.outatime.starwarsapp.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    const val BASE_URL = "https://swapi.dev/"
    const val IMG_URL = "https://aulavirtual.amaurypm.com/cm2023-1/" //https://aulavirtual.amaurypm.com/cm2023-1/sw1.jpg
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

    val CHARACTER_MOVIES: Map<String, String> = mapOf(Pair("Luke Skywalker", "A New Hope"),
                                        Pair("Luke Skywalker", "The Empire Strikes Back"),
                                        Pair("Luke Skywalker", "Return of the Jedi"),
                                        Pair("Luke Skywalker", "Revenge of the Sith"),
                                        Pair("C-3PO", "A New Hope"),
                                        Pair("C-3PO", "The Empire Strikes Back"),
                                        Pair("C-3PO", "Return of the Jedi"),
                                        Pair("C-3PO", "The Phantom Menace"),
                                        Pair("C-3PO", "Attack of the Clones"),
                                        Pair("C-3PO", "Revenge of the Sith"),
                                        Pair("R2-D2", "A New Hope"),
                                        Pair("R2-D2", "The Empire Strikes Back"),
                                        Pair("R2-D2", "Return of the Jedi"),
                                        Pair("R2-D2", "The Phantom Menace"),
                                        Pair("R2-D2", "Attack of the Clones"),
                                        Pair("R2-D2", "Revenge of the Sith"),
                                        Pair("Darth Vader", "A New Hope"),
                                        Pair("Darth Vader", "The Empire Strikes Back"),
                                        Pair("Darth Vader", "Return of the Jedi"),
                                        Pair("Darth Vader", "Revenge of the Sith"),
                                        Pair("Leia Organa", "A New Hope"),
                                        Pair("Leia Organa", "The Empire Strikes Back"),
                                        Pair("Leia Organa", "Return of the Jedi"),
                                        Pair("Leia Organa", "Revenge of the Sith"),
                                        Pair("Owen Lars", "A New Hope"),
                                        Pair("Owen Lars", "Attack of the Clones"),
                                        Pair("Owen Lars", "Revenge of the Sith"),
                                        Pair("Beru Whitesun lars", "A New Hope"),
                                        Pair("Beru Whitesun lars", "Attack of the Clones"),
                                        Pair("Beru Whitesun lars", "Revenge of the Sith"),
                                        Pair("R5-D4", "A New Hope"),
                                        Pair("Biggs Darklighter", "A New Hope"),
                                        Pair("Obi-Wan Kenobi", "A New Hope"),
                                        Pair("Obi-Wan Kenobi", "The Empire Strikes Back"),
                                        Pair("Obi-Wan Kenobi", "Return of the Jedi"),
                                        Pair("Obi-Wan Kenobi", "The Phantom Menace"),
                                        Pair("Obi-Wan Kenobi", "Attack of the Clones"),
                                        Pair("Obi-Wan Kenobi", "Revenge of the Sith"))

    val MOVIES: Map<Int, Int> = mapOf(Pair(1,4),
                                    Pair(2,5),
                                    Pair(3,6),
                                    Pair(4,1),
                                    Pair(5,2),
                                    Pair(6,3))

    val MOVIES_IMG: Map<Int, String> = mapOf(Pair(4,"sw1.jpg"),
        Pair(5,"sw2.jpg"),
        Pair(6,"sw3.jpg"),
        Pair(1,"sw4.jpg"),
        Pair(2,"sw5.jpg"),
        Pair(3,"sw6.jpg"))

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitImg(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(IMG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}