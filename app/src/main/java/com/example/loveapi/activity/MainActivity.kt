package com.example.loveapi.activity

import com.example.loveapi.fragment.LoveCalculatorFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loveapi.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoveCalculatorFragment())
                .commit()
        }
    }
}