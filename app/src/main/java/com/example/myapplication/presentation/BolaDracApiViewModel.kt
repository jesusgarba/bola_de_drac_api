package com.example.myapplication.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion.Characters
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.CharacterPagingSource
import com.example.myapplication.data.network.DragonBallApiRepository
import com.example.myapplication.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class BolaDracApiViewModel @Inject constructor(private val dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    val charactersPaging: Flow<PagingData<Character>> = dragonBallApiRepository.getAllCharacter()

}

