package com.outatime.starwarsapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.outatime.starwarsapp.R
import com.outatime.starwarsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}