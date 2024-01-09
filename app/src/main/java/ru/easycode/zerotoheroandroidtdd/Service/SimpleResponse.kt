package ru.easycode.zerotoheroandroidtdd.Service

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("text")
    val text : String)
