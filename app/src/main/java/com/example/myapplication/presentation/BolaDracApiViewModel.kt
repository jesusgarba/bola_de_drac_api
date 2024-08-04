package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.DragonBallApiRepository
import com.example.myapplication.data.network.response.DragonBallApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BolaDracApiViewModel @Inject constructor(dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    private val repository = dragonBallApiRepository
    private var characters: Response<DragonBallApiResponse>? = null

    fun onGetCharacters(){
        viewModelScope.launch {
             characters = repository.getAllCharacters()
            Log.i("dragonBallApi", "-----> $characters")
        }
    }
}

