package com.lifeforge.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Gender
import com.lifeforge.app.domain.usecase.CreateCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterCreationViewModel @Inject constructor(
    private val createCharacterUseCase: CreateCharacterUseCase
) : ViewModel() {

    suspend fun createCharacter(
        name: String,
        gender: Gender,
        country: String,
        city: String,
        bodyType: BodyType
    ) {
        createCharacterUseCase.execute(
            name = name,
            gender = gender,
            country = country,
            city = city,
            bodyType = bodyType
        )
    }
}
