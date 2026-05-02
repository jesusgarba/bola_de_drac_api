package com.example.myapplication.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Initial

@Serializable
data class Detail(val id: Int)

@Serializable
object Openings
