package com.example.greedygame.model
import com.google.gson.annotations.SerializedName

data class ChildrenModel(
    @SerializedName("data") val childrenDataModel: ChildrenDataModel)