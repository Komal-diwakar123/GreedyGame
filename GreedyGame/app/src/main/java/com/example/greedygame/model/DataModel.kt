package com.example.greedygame.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("children") val childrenModelList : List<ChildrenModel>)