package com.example.greedygame.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("images") val imageList: List<ImageDataModel>)