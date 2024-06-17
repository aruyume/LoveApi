package com.example.loveapi.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApiService {

    @GET("getPercentage")
    fun getPercentage(
        @Header("X-Rapidapi-Key") key: String,
        @Header("X-RapiDapi-Host") host: String,
        @Query("fname") firstName: String,
        @Query("sname") secondName: String
    ): Call<LoveResult>
}