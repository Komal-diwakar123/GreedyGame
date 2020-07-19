package com.example.greedygame.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.greedygame.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReddItRepo(private val context: Context){

    private var reddItRestApi: ReddItRestAPI = APIRetrofitService.createApiService()
    val responseLiveData = MutableLiveData<ResponseModel>()
    val TAG = "ReddItRepo"

    fun getReddItResponse(){
        val reddItResponse = reddItRestApi.getReddItResponse()
        Log.d(TAG,"enqueue started")
        reddItResponse.enqueue( object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                Log.d(TAG,"onResponse finished")
                if (response.isSuccessful){
                    responseLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseModel>, error: Throwable?) {
                Toast.makeText(context, error?.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}