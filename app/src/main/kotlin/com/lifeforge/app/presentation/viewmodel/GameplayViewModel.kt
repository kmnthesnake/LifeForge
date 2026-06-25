package com.lifeforge.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.LifeEvent
import com.lifeforge.app.domain.model.SimulationResult
import com.lifeforge.app.domain.model.StatChange
import com.lifeforge.app.engine.CharacterGenerator
import com.lifeforge.app.engine.simulation.SimulationEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameplayUiState(
    val character: Character? = null,
    val events: List<LifeEvent> = emptyList(),
    val currentEventIndex: Int = 0,
    val isEventDialogVisible: Boolean = false,
    val isLoading: Boolean = false,
    val statChanges: StatChange? = null,
    val recentEvents: List<LifeEvent> = emptyList()
)

@HiltViewModel
class GameplayViewModel @Inject constructor(
    private val simulationEngine: SimulationEngine,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameplayUiState())
    val uiState: StateFlow<GameplayUiState> = _uiState.asStateFlow()

    private val _recentEvents = MutableStateFlow<List<LifeEvent>>(emptyList())
    val recentEvents: StateFlow<List<LifeEvent>> = _recentEvents.asStateFlow()

    fun startNewGame() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Delete previous saves
            characterRepository.deleteAll()
            
            // Generate new character
            val newCharacter = CharacterGenerator.generate()
            
            // Save the new character
            characterRepository.saveCharacter(newCharacter)
            
            _uiState.value = _uiState.value.copy(
                character = newCharacter,
                isLoading = false
            )
            _recentEvents.value = emptyList()
        }
    }

    fun loadGame() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            characterRepository.getLastCharacter()
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.Eagerly,
                    initialValue = null
                ).collect { character ->
                    if (character != null) {
                        _uiState.value = _uiState.value.copy(
                            character = character,
                            isLoading = false
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            character = null,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun ageUp() {
        viewModelScope.launch {
            val currentCharacter = _uiState.value.character ?: return@launch
            
            val result: SimulationResult = simulationEngine.advanceOneYear(currentCharacter)
            
            val updatedRecentEvents = (result.events + _recentEvents.value).take(5)
            
            _uiState.value = _uiState.value.copy(
                character = result.updatedCharacter,
                events = result.events,
                currentEventIndex = 0,
                isEventDialogVisible = result.events.isNotEmpty(),
                statChanges = result.statChanges,
                recentEvents = updatedRecentEvents
            )
            _recentEvents.value = updatedRecentEvents
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
