package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.DragonBallApiWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DragonBallApiService {

    @GET("api/characters")
    suspend fun getAllCharactersWrapper(@Query("page") page: Int): DragonBallApiWrapperResponse
}