package com.example.myapplication.core.navigation

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
object Initial

@Serializable
data class Detail(val id: Int)