package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.DragonBallApiRepository
import com.example.myapplication.data.network.response.DragonBallApiResponse
import com.example.myapplication.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BolaDracApiViewModel @Inject constructor(dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    private val repository = dragonBallApiRepository
    private var characters: List<Character?> = emptyList<Character>()

    fun onGetCharacters(){
        viewModelScope.launch {
             characters = repository.getAllCharacters()
            Log.i("dragonBallApi", "-----> $characters")
        }
    }
}

