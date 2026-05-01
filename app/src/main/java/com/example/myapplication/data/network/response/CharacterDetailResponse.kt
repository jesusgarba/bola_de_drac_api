package com.example.myapplication.data.network.response

import com.example.myapplication.presentation.model.Character
import com.example.myapplication.presentation.model.Planet
import com.example.myapplication.presentation.model.Transformation
import com.google.gson.annotations.SerializedName

data class CharacterDetailResponse(
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
    @SerializedName("originPlanet") val originPlanet: PlanetResponse?,
    @SerializedName("transformations") val transformations: List<TransformationResponse>?,
) {
    fun toPresentation(): Character {
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
            deletedAt = deletedAt,
            originPlanet = originPlanet?.toPresentation(),
            transformations = transformations?.map { it.toPresentation() }.orEmpty()
        )
    }
}

data class PlanetResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("isDestroyed") val isDestroyed: Boolean?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("deletedAt") val deletedAt: String?,
) {
    fun toPresentation(): Planet {
        return Planet(
            id = id,
            name = name,
            isDestroyed = isDestroyed,
            description = description,
            image = image,
            deletedAt = deletedAt,
        )
    }
}

data class TransformationResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("ki") val ki: String?,
    @SerializedName("deletedAt") val deletedAt: String?,
) {
    fun toPresentation(): Transformation {
        return Transformation(
            id = id,
            name = name,
            image = image,
            ki = ki,
            deletedAt = deletedAt,
        )
    }
}
