package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.DragonBallApiResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface DragonBallApiService {
    @GET("api/characters")
    suspend fun getCharacters(@Query("currentPage") page: Int): DragonBallApiResponse
}