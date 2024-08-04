package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.DragonBallApiResponse
import com.example.myapplication.presentation.model.Character
import retrofit2.Response

import javax.inject.Inject

class DragonBallApiRepository @Inject constructor(private val api: DragonBallApiService) {
    suspend  fun getAllCharacters(): List<Character?>{

        val characters: MutableList<Character> = emptyList<Character>().toMutableList()

        val apiResponse = api.getCharacters().body()
        apiResponse?.items?.forEach {
            val character = Character(it.id, it.name, it.ki, it.maxKi, it.race, it.gender, it.description, it.image, it.affiliation, it.deletedAt)
            characters.add(character)
        }

        return characters
    }
}