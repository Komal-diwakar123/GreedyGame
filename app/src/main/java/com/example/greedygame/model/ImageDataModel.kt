package com.example.greedygame.model

import com.google.gson.annotations.SerializedName

data class ImageDataModel(
    @SerializedName("source") val sourceModel: SourceModel,
    @SerializedName("resolutions") val resolutionList: List<SourceModel>)