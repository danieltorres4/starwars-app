package com.outatime.starwarsapp.view.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.ActivityFilmDetailsBinding
import com.outatime.starwarsapp.model.MovieDetail
import com.outatime.starwarsapp.model.StarWarsApi
import com.outatime.starwarsapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetails : AppCompatActivity() {
    private lateinit var binding: ActivityFilmDetailsBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this@FilmDetails, R.raw.sw2)
        mediaPlayer.start()

        val bundle = intent.extras
        val filmTitle = bundle?.getString("title","")
        val epId = bundle?.getInt("episode_id",0)
        val fN = bundle?.getStringArrayList("filmNumber")
        var fid: String = ""
        var fImg: String = ""

        val filmArray = fN?.toArray()

        for ((filmNumber, filmId) in Constants.MOVIES) {
            if(filmId == epId) {
                fid = filmNumber.toString()
            }
        }

        for ((filmId, filmStr) in Constants.MOVIES_IMG) {
            if(filmId == epId) {
                fImg = filmStr
            }
        }

        var imgURL: String = Constants.IMG_URL + fImg

        val call = Constants.getRetrofit().create(StarWarsApi::class.java).getFilmDetails(fid)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object: Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    binding.pbConnection.visibility = View.GONE
                    binding.tvFilmTitle.text = getString(R.string.sw_plus_title, response.body()?.title)

                    if (filmArray != null && filmArray.contains(fid)) {
                        with(binding) {
                            Glide.with(this@FilmDetails).load(imgURL).into(ivFilmImg)
                            tvAppear.text = getString(R.string.appears_in_movie)
                            lavNotFound.isVisible = false
                        }
                    } else {
                        with(binding) {
                            lavNotFound.isVisible = true
                            tvAppear.text = getString(R.string.does_not_appear_in_movie)
                        }
                    }

                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    binding.pbConnection.visibility = View.GONE
                    Toast.makeText(this@FilmDetails, getString(R.string.failure_message, t.message), Toast.LENGTH_SHORT).show()
                }

            })

        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.start()
    }
}