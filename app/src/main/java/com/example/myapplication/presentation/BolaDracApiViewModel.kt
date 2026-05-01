package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.myapplication.data.network.DragonBallApiRepository
import com.example.myapplication.presentation.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BolaDracApiViewModel @Inject constructor(private val dragonBallApiRepository: DragonBallApiRepository): ViewModel() {

    val charactersPaging: Flow<PagingData<Character>> = dragonBallApiRepository.getAllCharacter()

    private val _characterDetailState =
        MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val characterDetailState: StateFlow<CharacterDetailUiState> = _characterDetailState.asStateFlow()

    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            _characterDetailState.value = CharacterDetailUiState.Loading
            runCatching {
                dragonBallApiRepository.getCharacterById(id)
            }.onSuccess { character ->
                _characterDetailState.value = CharacterDetailUiState.Success(character)
            }.onFailure { throwable ->
                _characterDetailState.value = CharacterDetailUiState.Error(
                    throwable.message ?: "No se ha podido cargar el detalle del personaje"
                )
            }
        }
    }
}

sealed class CharacterDetailUiState {
    data object Loading : CharacterDetailUiState()
    data class Success(val character: Character) : CharacterDetailUiState()
    data class Error(val message: String) : CharacterDetailUiState()
}
