package com.example.greedygame.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greedygame.model.ResponseModel
import com.example.greedygame.repo.ReddItRepo

class ReddItViewModel : ViewModel(){

    private lateinit var reddItRepo: ReddItRepo
    fun init(context: Context){
        reddItRepo= ReddItRepo(context)
    }

    fun getRedItLiveData(): MutableLiveData<ResponseModel> {
        return reddItRepo.responseLiveData
    }

    fun getReddItResponse(){
        reddItRepo.getReddItResponse()
    }
}