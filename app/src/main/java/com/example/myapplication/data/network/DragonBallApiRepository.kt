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

class DragonBallApiRepository @Inject constructor(private val api: DragonBallApiService) {
    companion object {
        const val MAX_ITEM = 10
        const val PREFECTH_ITEMS = 3
    }

    fun getAllCharacters(): Flow<PagingData<Character>> {

        /*  val characters: MutableList<Character> = emptyList<Character>().toMutableList()

          val apiResponse = api.getCharacters().body()
          apiResponse?.items?.forEach {
              val character = Character(it.id, it.name, it.ki, it.maxKi, it.race, it.gender, it.description, it.image, it.affiliation, it.deletedAt)
              characters.add(character)
          }

          return characters*/
        return Pager(
            config = PagingConfig(pageSize = MAX_ITEM, prefetchDistance = PREFECTH_ITEMS),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }).flow
    }
}