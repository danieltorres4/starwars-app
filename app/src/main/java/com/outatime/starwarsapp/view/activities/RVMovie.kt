package com.outatime.starwarsapp.view.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.ActivityRvmovieBinding
import com.outatime.starwarsapp.model.Movie
import com.outatime.starwarsapp.model.StarWarsApi
import com.outatime.starwarsapp.util.Constants
import com.outatime.starwarsapp.view.adapters.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RVMovie : AppCompatActivity() {
    private lateinit var binding: ActivityRvmovieBinding
    private lateinit var mp: MediaPlayer
    private lateinit var fN: String
    var filmNumber: ArrayList<String> = arrayListOf<String>()
    private lateinit var filmN: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRvmovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mp = MediaPlayer.create(this@RVMovie, R.raw.sw1)
        mp.start()

        var movieBundle = intent.extras

        var filmEP = movieBundle?.getStringArrayList("films")
        if (filmEP != null) {
            for (m in filmEP) {
                fN = m.substring(28,29)
                filmNumber.add(fN)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val callMovie = Constants.getRetrofit().create(StarWarsApi::class.java).getFilms()

            callMovie.enqueue(object: Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Toast.makeText(this@RVMovie, R.string.successful_message, Toast.LENGTH_SHORT).show()
                    binding.rvMovieMenu.layoutManager = LinearLayoutManager(this@RVMovie)
                    binding.rvMovieMenu.adapter = MovieAdapter(this@RVMovie, response.body()!!)
                    binding.pbMovieConnection.visibility = View.GONE

                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    binding.pbMovieConnection.visibility = View.GONE
                    Toast.makeText(this@RVMovie, getString(R.string.failure_message, t.message), Toast.LENGTH_LONG).show()
                }

            })
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

    fun selectedMovie(movie: Movie, position: Int) {
        val param = Bundle()

        param.apply {
            putString("title", movie.results[position].title)
            putInt("episode_id", movie.results[position].episode_id)
            putStringArrayList("filmNumber", filmNumber)
        }

        val intent = Intent(this@RVMovie, FilmDetails::class.java)
        intent.putExtras(param)

        startActivity(intent)
    }
}