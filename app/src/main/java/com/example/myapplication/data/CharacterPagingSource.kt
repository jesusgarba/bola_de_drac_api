package com.example.myapplication.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.network.DragonBallApiService
import com.example.myapplication.data.network.response.DragonBallApiWrapperResponse
import com.example.myapplication.data.network.response.ItemsResponse
import com.example.myapplication.presentation.model.Character
import okio.IOException
import javax.inject.Inject

class CharacterPagingSource  @Inject constructor(private val api:DragonBallApiService):
    PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?:1
            val response: DragonBallApiWrapperResponse= api.getAllCharactersWrapper(page)
            val characters: List<ItemsResponse> = response.items

            val prevKey = if (page > 0) page -1 else null
            val nextKey = if (response.meta.currentPage != null) page + 1 else null
            LoadResult.Page(
                data = characters.map {it.toPresentation() },
                prevKey = prevKey, nextKey = nextKey
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        }
    }
}