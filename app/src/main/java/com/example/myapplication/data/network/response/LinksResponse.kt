package com.example.myapplication.data.network.response

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("first") val first: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("next") val next: String?,
    @SerializedName("last") val last: String?,
)
