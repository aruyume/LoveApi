package com.example.loveapi.api

import com.example.loveapi.model.LoveResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApiService {

    @GET("getPercentage")
    fun getPercentage(
        @Header("X-Rapidapi-Key") key: String = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
        @Header("X-RapiDapi-Host") host: String = "love-calculator.p.rapidapi.com",
        @Query("fname") firstName: String,
        @Query("sname") secondName: String
    ): Call<LoveResult>
}