package com.example.myapplication.presentation.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("ki") val ki: String?,
    @SerializedName("maxKi") val maxKi: String?,
    @SerializedName("race") val race: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("affiliation") val affiliation: String?,
    @SerializedName("deletedAt") val deletedAt: String?,
    @SerializedName("originPlanet") val originPlanet: Planet? = null,
    @SerializedName("transformations") val transformations: List<Transformation>? = null,
)

data class Planet(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("isDestroyed") val isDestroyed: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("deletedAt") val deletedAt: String?,
)

data class Transformation(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("ki") val ki: String?,
    @SerializedName("deletedAt") val deletedAt: String?,
)
