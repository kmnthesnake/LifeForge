package com.lifeforge.app.domain.usecase

import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.data.repository.GameEventRepository
import com.lifeforge.app.data.local.entity.GameEventEntity
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.CharacterStats
import com.lifeforge.app.domain.model.RandomEvents
import kotlin.math.max
import kotlin.math.min
import javax.inject.Inject

class AgeCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val gameEventRepository: GameEventRepository
) {
    suspend fun execute(character: Character): Character {
        val newAge = character.age + 1
        val (eventTitle, statChanges) = RandomEvents.getRandomEvent() ?: ("Aged" to emptyMap())
        val moneyChange = RandomEvents.getRandomMoneyEvent()

        val newStats = character.stats.copy(
            health = clampStat(character.stats.health + (statChanges["health"] ?: 0)),
            fitness = clampStat(character.stats.fitness + (statChanges["fitness"] ?: 0)),
            looks = clampStat(character.stats.looks - (newAge / 20) + (statChanges["looks"] ?: 0)),
            intelligence = clampStat(character.stats.intelligence + (statChanges["intelligence"] ?: 0))
        )

        val newMoney = max(0L, character.money + moneyChange)
        val updatedCharacter = character.copy(
            age = newAge,
            stats = newStats,
            money = newMoney,
            updatedAt = System.currentTimeMillis()
        )

        characterRepository.updateCharacter(updatedCharacter)

        val event = GameEventEntity(
            characterId = character.id,
            title = eventTitle,
            description = "At age $newAge: $eventTitle",
            age = newAge,
            healthChange = statChanges["health"] ?: 0,
            fitnessChange = statChanges["fitness"] ?: 0,
            looksChange = statChanges["looks"] ?: 0,
            intelligenceChange = statChanges["intelligence"] ?: 0,
            moneyChange = moneyChange,
            timestamp = System.currentTimeMillis()
        )

        gameEventRepository.addEvent(event)

        return updatedCharacter
    }

    private fun clampStat(value: Int): Int = min(100, max(1, value))
}
