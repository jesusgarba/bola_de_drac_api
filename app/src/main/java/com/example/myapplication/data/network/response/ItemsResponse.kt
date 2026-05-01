package com.example.myapplication.data.network.response

import com.example.myapplication.presentation.model.Character
import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ki") val ki: String,
    @SerializedName("maxKi") val maxKi: String,
    @SerializedName("race") val race: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("affiliation") val affiliation: String,
    @SerializedName("deletedAt") val deletedAt: String,
){
    fun toPresentation():Character{
        return Character(
            id = id,
            name = name,
            ki = ki,
            maxKi = maxKi,
            race = race,
            gender = gender,
            description = description,
            image = image,
            affiliation = affiliation,
            deletedAt = deletedAt
        )
    }
}
