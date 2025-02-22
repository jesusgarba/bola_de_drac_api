package com.example.myapplication.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.myapplication.data.network.DragonBallApiRepository
import com.example.myapplication.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    val charactersPaging: Flow<PagingData<Character>> = dragonBallApiRepository.getAllCharacter()

}

