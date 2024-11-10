package com.example.myapplication.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.data.CharacterPagingSource
import com.example.myapplication.data.network.response.DragonBallApiResponse
import com.example.myapplication.presentation.model.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

import javax.inject.Inject

class DragonBallApiRepository @Inject constructor( val api: DragonBallApiService) {

    suspend fun getAllCharacters(): List<Character> {

        val characters: MutableList<Character> = emptyList<Character>().toMutableList()

        for (page in 1..6){
            val apiResponse = api.getCharacters(page)
            apiResponse.items.forEach {
                val character = Character(
                    it.id,
                    it.name,
                    it.ki,
                    it.maxKi,
                    it.race,
                    it.gender,
                    it.description,
                    it.image,
                    it.affiliation,
                    it.deletedAt
                )
                characters.add(character)
            }
        }
        return characters

    }
}