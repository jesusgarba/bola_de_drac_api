package com.example.myapplication.data.network.response

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class TransformationResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("ki") val ki: String
)
