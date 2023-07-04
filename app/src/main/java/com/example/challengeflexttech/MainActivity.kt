package com.example.challengeflexttech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.challengeflexttech.databinding.ActivityMainBinding
import com.example.challengeflexttech.model.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val nasHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_Container) as NavHostFragment
        val navControler = nasHostFragment.navController
    }
}