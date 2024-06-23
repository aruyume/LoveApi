package com.example.loveapi

import android.app.Application
import com.example.loveapi.api.LoveApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    var api: LoveApiService = Retrofit.Builder()
        .baseUrl("https://love-calculator.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(LoveApiService::class.java)
}