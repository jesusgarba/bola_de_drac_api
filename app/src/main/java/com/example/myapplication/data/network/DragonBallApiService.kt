package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.DragonBallApiResponse
import retrofit2.Response

import retrofit2.http.GET

interface DragonBallApiService {
    @GET("api/characters")
    suspend fun getCharacters(): Response<DragonBallApiResponse>
}