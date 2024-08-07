package com.example.myapplication.data.network.response

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int?,
)
