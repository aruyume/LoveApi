package com.example.loveapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.loveapi.R
import com.example.loveapi.api.LoveApiService
import com.example.loveapi.databinding.ActivityMainBinding
import com.example.loveapi.sharedpreference.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    @Inject
    lateinit var loveApiService: LoveApiService

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (sharedPreferencesHelper.isOnBoardShown()) {
            navController.navigate(R.id.action_to_loveCalculatorFragment)
        } else {
            navController.navigate(R.id.action_to_onBoardFragment)
        }
    }
}