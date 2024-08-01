package com.example.myapplication.data.Network

import com.example.myapplication.data.Network.response.CharacterResponseWrapper
import retrofit2.http.GET

interface DragonBallApiService {
    @GET("/api/characters")
    suspend fun getCharacters(): List<CharacterResponseWrapper>
}