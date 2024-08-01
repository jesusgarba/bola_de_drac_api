package com.example.myapplication.data.Network.response

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
)
