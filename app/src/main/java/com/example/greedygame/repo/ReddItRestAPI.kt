package com.example.greedygame.repo

import com.example.greedygame.model.ResponseModel
import retrofit2.http.GET

interface ReddItRestAPI{
    companion object {
        val BASE_URL = "https://www.reddit.com/r/images/"
    }
    @GET("hot.json")
    fun getReddItResponse(): retrofit2.Call<ResponseModel>
}