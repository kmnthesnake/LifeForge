package com.lifeforge.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.LifeEvent
import com.lifeforge.app.engine.CharacterGenerator
import com.lifeforge.app.engine.time.TimeEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameplayUiState(
    val character: Character? = null,
    val events: List<LifeEvent> = emptyList(),
    val currentEventIndex: Int = 0,
    val isEventDialogVisible: Boolean = false,
    val isLoading: Boolean = false
)

@HiltViewModel
class GameplayViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GameplayUiState())
    val uiState: StateFlow<GameplayUiState> = _uiState.asStateFlow()

    fun startNewGame() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val newCharacter = CharacterGenerator.generate()
            _uiState.value = _uiState.value.copy(
                character = newCharacter,
                isLoading = false
            )
        }
    }

    fun ageUp() {
        viewModelScope.launch {
            val currentCharacter = _uiState.value.character ?: return@launch
            val (updatedCharacter, events) = TimeEngine.ageUp(currentCharacter)

            _uiState.value = _uiState.value.copy(
                character = updatedCharacter,
                events = events,
                currentEventIndex = 0,
                isEventDialogVisible = events.isNotEmpty()
            )
        }
    }

    fun nextEvent() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentEventIndex + 1

        if (nextIndex < currentState.events.size) {
            _uiState.value = _uiState.value.copy(
                currentEventIndex = nextIndex
            )
        } else {
            dismissEventDialog()
        }
    }

    fun dismissEventDialog() {
        _uiState.value = _uiState.value.copy(
            isEventDialogVisible = false,
            currentEventIndex = 0,
            events = emptyList()
        )
    }
}
