package com.outatime.starwarsapp.view.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var planetDiameter: String
    private lateinit var planetClimate: String
    private lateinit var planetOrbitalPeriod: String
    private lateinit var planetRotationPeriod: String
    private lateinit var planetPopulation: String
    private lateinit var planetTerrain: String
    private lateinit var films: ArrayList<String>

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
                        films = response.body()?.films!!
                        tvFilmsDetails.text = getString(R.string.tv_result_films)
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
                        registerForContextMenu(binding.tvPlanetDetails)
                        tvPlanetDetails.text = getString(R.string.tv_result_planet_name, response.body()?.name)
                        planetClimate = response.body()?.climate.toString()
                        planetDiameter = response.body()?.diameter.toString()
                        planetOrbitalPeriod = response.body()?.orbital_period.toString()
                        planetPopulation = response.body()?.population.toString()
                        planetRotationPeriod = response.body()?.rotation_period.toString()
                        planetTerrain = response.body()?.terrain.toString()
                    }
                }

                override fun onFailure(call: Call<PlanetDetails>, t: Throwable) {
                    Toast.makeText(this@CharacterDetails, getString(R.string.failure_message, t.message), Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_planet_item, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_1 -> {
                //item.setTitle(planetClimate)
                Toast.makeText(this@CharacterDetails, planetClimate, Toast.LENGTH_SHORT).show()
            }

            R.id.item_2 -> {
                Toast.makeText(this@CharacterDetails, planetDiameter, Toast.LENGTH_SHORT).show()
            }

            R.id.item_3 -> {
                Toast.makeText(this@CharacterDetails, planetOrbitalPeriod, Toast.LENGTH_SHORT).show()
            }

            R.id.item_4 -> {
                Toast.makeText(this@CharacterDetails, planetPopulation, Toast.LENGTH_SHORT).show()
            }

            R.id.item_5 -> {
                Toast.makeText(this@CharacterDetails, planetRotationPeriod, Toast.LENGTH_SHORT).show()
            }

            R.id.item_6 -> {
                Toast.makeText(this@CharacterDetails, planetTerrain, Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        mp.pause()
    }

    override fun onRestart() {
        super.onRestart()
        mp.start()
    }

    fun onClick(view: View) {
        val ad = AlertDialog.Builder(this@CharacterDetails)
        ad.setTitle(R.string.alert_dialog_title)
        ad.setMessage(R.string.alert_dialog_msg)

        ad.setPositiveButton(R.string.alert_dialog_positive_response) {dialog, which ->

        }

        ad.show()
    }

    fun onClickMovie(view: View) {
        val intent = Intent(this@CharacterDetails, RVMovie::class.java)
        val param = Bundle()

        with(binding) {
            param.putStringArrayList("films", films)
        }

        intent.putExtras(param)
        startActivity(intent)
    }
}