package com.outatime.starwarsapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.outatime.starwarsapp.databinding.ActivityMainBinding
import com.outatime.starwarsapp.model.StarWarsApi
import com.outatime.starwarsapp.util.Constants
import com.outatime.starwarsapp.model.Character
import com.outatime.starwarsapp.model.People
import com.outatime.starwarsapp.view.adapters.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val call = Constants.getRetrofit().create(StarWarsApi::class.java).getCharacters()

            call.enqueue(object: Callback<People> {
                override fun onResponse(call: Call<People>, response: Response<People>) {
                    Log.d(Constants.LOGTAG, "Server response: ${response.toString()}")
                    Log.d(Constants.LOGTAG, "Data: ${response.body().toString()}")
                    Toast.makeText(this@MainActivity, "Successful Connection!", Toast.LENGTH_LONG).show()

                    binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvMenu.adapter = Adapter(this@MainActivity, response.body()!!)

                    binding.pbConnection.visibility = View.GONE
                }

                override fun onFailure(call: Call<People>, t: Throwable) {
                    binding.pbConnection.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Failure Connection! ${t.message}", Toast.LENGTH_LONG).show()
                    print("\n\nFailure Connection! ${t.message} \n\n")
                }

            })
        }


    }

    fun selectedCharacter(result: People) {
        //Click of every element of the recycler view
    }
}
