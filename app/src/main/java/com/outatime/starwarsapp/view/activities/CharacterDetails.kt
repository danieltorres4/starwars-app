package com.outatime.starwarsapp.view.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.ActivityCharacterDetailsBinding
import com.outatime.starwarsapp.model.*
import com.outatime.starwarsapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetails : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailsBinding
    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mp = MediaPlayer.create(this@CharacterDetails, R.raw.sw2)
        mp.start()

        val bundle = intent.extras

        var characterNumber: Int = 0
        var planetNumber: Int = 0

        val name = bundle?.getString("name","")

        for ((clave, valor) in Constants.CHARACTERS){
            if (clave.equals(name.toString())) {
                characterNumber = valor
            }
        }

        for ((clave, valor) in Constants.PLANETS){
            if (clave.equals(name.toString())) {
                planetNumber = valor
            }
        }

        val call = Constants.getRetrofit().create(StarWarsApi::class.java).getCharacterDetail(characterNumber)
        val call2 = Constants.getRetrofit().create(StarWarsApi::class.java).getPlanetDetail(planetNumber)

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object: Callback<CharacterDetail> {
                override fun onResponse(
                    call: Call<CharacterDetail>,
                    response: Response<CharacterDetail>
                ) {
                    binding.pbConnection.visibility = View.GONE

                    //Log.d(Constants.LOGTAG, "Server response: ${response.toString()}")
                    //Log.d(Constants.LOGTAG, "Data: ${response.body().toString()}")

                    with(binding) {
                        tvNameDetails.text = response.body()?.name
                        tvHeightDetails.text = getString(R.string.tv_result_height, response.body()?.height)
                        tvBirthdayDetails.text = getString(R.string.tv_result_birthday, response.body()?.birthday)
                        tvGenderDetails.text = getString(R.string.tv_result_gender, response.body()?.gender)
                    }
                }

                override fun onFailure(call: Call<CharacterDetail>, t: Throwable) {
                    binding.pbConnection.visibility = View.GONE
                    Toast.makeText(this@CharacterDetails, getString(R.string.failure_message, t.message), Toast.LENGTH_LONG).show()
                }

            })

            call2.enqueue(object: Callback<PlanetDetails> {
                override fun onResponse(
                    call: Call<PlanetDetails>,
                    response: Response<PlanetDetails>
                ) {
                    //Log.d(Constants.LOGTAG, "Server response: ${response.toString()}")
                    //Log.d(Constants.LOGTAG, "Data: ${response.body().toString()}")

                    with(binding) {
                        tvPlanetDetails.text = getString(R.string.tv_result_planet_name, response.body()?.name)
                    }
                }

                override fun onFailure(call: Call<PlanetDetails>, t: Throwable) {
                    Toast.makeText(this@CharacterDetails, getString(R.string.failure_message, t.message), Toast.LENGTH_LONG).show()
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
}