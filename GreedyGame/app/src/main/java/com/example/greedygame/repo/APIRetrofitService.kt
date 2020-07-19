package com.example.greedygame.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRetrofitService{
    companion object {
        private var retrofit: Retrofit? = null
        fun createApiService(): ReddItRestAPI {
            if (retrofit == null) {
                createNewInstance()
            }
            return retrofit!!.create(ReddItRestAPI::class.java)
        }


        private fun createNewInstance() {
            retrofit = Retrofit.Builder()
                .baseUrl(ReddItRestAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}