package com.example.loveapi.model

import com.google.gson.annotations.SerializedName

data class LoveResult(
    @SerializedName("fname")
    val firstName: String,
    @SerializedName("sname")
    val secondName: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("result")
    val result: String
)
