package com.example.loveapi.mvp

import com.example.loveapi.retrofit.App
import com.example.loveapi.retrofit.LoveResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveModel : LoveContract.Model {

    private val api = App().api

    override fun getLovePercentage(
        firstName: String,
        secondName: String,
        callback: (result: LoveResult?, error: String?) -> Unit
    ) {
        api?.getPercentage(
            firstName = firstName,
            key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
            host = "love-calculator.p.rapidapi.com",
            secondName = secondName
        )?.enqueue(object : Callback<LoveResult> {
            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoveResult>, throwable: Throwable) {
                callback(null, throwable.message)
            }
        })
    }
}