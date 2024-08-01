package com.example.myapplication.presentation

import android.util.Log
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Network.DragonBallApiRepository
import com.example.myapplication.data.Network.response.CharacterResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BolaDracApiViewModel @Inject constructor(dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    val repository = dragonBallApiRepository
    var characters:List<CharacterResponseWrapper>? = null

    fun onGetCharacters(){
        viewModelScope.launch {
             characters = repository.getAllCharacters()
            Log.i("dragonBallApi", "-----> $characters")
        }
    }
}

