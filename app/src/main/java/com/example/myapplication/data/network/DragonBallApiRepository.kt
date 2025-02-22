package com.example.myapplication.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.data.CharacterPagingSource
import com.example.myapplication.data.network.response.CharacterApiWraperResponse
import com.example.myapplication.presentation.model.Character
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class DragonBallApiRepository @Inject constructor( val api: DragonBallApiService) {

    companion object{
        const val MAX_ITEMS = 10
        const val PREFECTH_ITEMS = 3
    }

    fun getAllCharacter(): Flow<PagingData<Character>>{
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFECTH_ITEMS),
            pagingSourceFactory = {
                CharacterPagingSource(api = api)
            }).flow
    }

    suspend fun getCharacterById(id: Int): CharacterApiWraperResponse{
       val response: CharacterApiWraperResponse =  api.getCharacterWrapper(id)
        return response
    }
}