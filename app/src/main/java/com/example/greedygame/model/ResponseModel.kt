package com.example.greedygame.model
import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("data") val data : DataModel)