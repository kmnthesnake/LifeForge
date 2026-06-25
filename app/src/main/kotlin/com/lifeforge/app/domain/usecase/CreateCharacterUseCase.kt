package com.lifeforge.app.domain.usecase

import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.CharacterStats
import com.lifeforge.app.domain.model.Gender
import javax.inject.Inject

class CreateCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun execute(
        name: String,
        gender: Gender,
        country: String,
        city: String,
        bodyType: BodyType
    ): Character {
        val stats = CharacterStats(
            health = (50..100).random(),
            fitness = (50..100).random(),
            looks = (50..100).random(),
            intelligence = (50..100).random()
        )

        val character = Character(
            name = name,
            gender = gender,
            country = country,
            city = city,
            bodyType = bodyType,
            age = 1,
            stats = stats,
            money = 1000L
        )

        val id = characterRepository.saveCharacter(character)
        return character.copy(id = id)
    }
}
