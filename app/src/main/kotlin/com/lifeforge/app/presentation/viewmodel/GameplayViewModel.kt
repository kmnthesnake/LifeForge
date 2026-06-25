package com.lifeforge.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.usecase.AgeCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameplayViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val ageCharacterUseCase: AgeCharacterUseCase
) : ViewModel() {

    val character: Flow<Character?> = characterRepository.getLastCharacter()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun ageCharacter() {
        viewModelScope.launch {
            val currentCharacter = characterRepository.getLastCharacter().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = null
            ).value

            currentCharacter?.let {
                ageCharacterUseCase.execute(it)
            }
        }
    }
}
