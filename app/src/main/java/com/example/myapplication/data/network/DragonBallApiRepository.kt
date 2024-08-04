package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.DragonBallApiResponse
import retrofit2.Response

import javax.inject.Inject

class DragonBallApiRepository @Inject constructor(private val api: DragonBallApiService) {
    suspend  fun getAllCharacters(): Response<DragonBallApiResponse> {

        val apiResponse = api.getCharacters()
        return apiResponse
    }
}