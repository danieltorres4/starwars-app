package com.outatime.starwarsapp.view.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.ActivityMovieDetailsBinding
import com.outatime.starwarsapp.model.Movie
import com.outatime.starwarsapp.model.MovieDetail
import com.outatime.starwarsapp.model.StarWarsApi
import com.outatime.starwarsapp.util.Constants
import com.outatime.starwarsapp.view.adapters.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mp = MediaPlayer.create(this@MovieDetails, R.raw.sw1)
        mp.start()

        val movieBundle = intent.extras
        var filmCounter: Int = 0

        val filmEP = movieBundle?.getStringArrayList("films")
        if (filmEP != null) {
            for (m in filmEP) {
                filmCounter += 1
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (filmEP != null) {
                for (movie in filmEP) {
                    var filmNumber = movie.substring(28)
                    var fN = filmNumber.substring(0,1)
                    val callMovie = Constants.getRetrofit().create(StarWarsApi::class.java).getFilms()

                    callMovie.enqueue(object: Callback<Movie> {
                        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                            Toast.makeText(this@MovieDetails, response.body().toString(), Toast.LENGTH_SHORT).show()

                            with(binding) {
                                tvEpisodeId.text = response.body()?.results!![fN.toInt()].episode_id.toString()
                            }
                        }

                        override fun onFailure(call: Call<Movie>, t: Throwable) {
                            Toast.makeText(this@MovieDetails, getString(R.string.failure_message, t.message), Toast.LENGTH_SHORT).show()
                        }

                    })
                }
            }

        }
    }

    override fun onPause() {
        super.onPause()
        mp.pause()
    }

    override fun onRestart() {
        super.onRestart()
        mp.start()
    }

}