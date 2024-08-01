package com.example.myapplication.data.Network

import com.example.myapplication.data.Network.response.CharacterResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DragonBallApiRepository @Inject constructor(private val api: DragonBallApiService) {
    suspend  fun getAllCharacters(): List<CharacterResponseWrapper> {
        return api.getCharacters()
    }
}