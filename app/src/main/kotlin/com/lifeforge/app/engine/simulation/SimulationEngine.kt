package com.lifeforge.app.engine.simulation

import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.SimulationResult
import com.lifeforge.app.domain.model.StatChange
import com.lifeforge.app.engine.events.LifeEventGenerator
import com.lifeforge.app.engine.time.TimeEngine
import javax.inject.Inject

class SimulationEngine @Inject constructor(
    private val characterRepository: com.lifeforge.app.data.repository.CharacterRepository
) {

    /**
     * Advances the character's life by one year.
     * Internally calls TimeEngine and LifeEventGenerator.
     * Automatically saves the character to the database.
     */
    suspend fun advanceOneYear(character: Character): SimulationResult {
        // Store previous stats for delta calculation
        val previousHealth = character.health
        val previousHappiness = character.happiness
        val previousFitness = character.fitness
        val previousLooks = character.looks
        val previousIntelligence = character.intelligence
        val previousMoney = character.money

        // Advance time and get events
        val (updatedCharacter, events) = TimeEngine.ageUp(character)

        // Calculate stat changes
        val statChanges = StatChange(
            healthDelta = updatedCharacter.health - previousHealth,
            happinessDelta = updatedCharacter.happiness - previousHappiness,
            fitnessDelta = updatedCharacter.fitness - previousFitness,
            looksDelta = updatedCharacter.looks - previousLooks,
            intelligenceDelta = updatedCharacter.intelligence - previousIntelligence,
            moneyDelta = updatedCharacter.money - previousMoney
        )

        // Auto-save the character
        characterRepository.saveCharacter(updatedCharacter)

        return SimulationResult(
            updatedCharacter = updatedCharacter,
            events = events,
            statChanges = statChanges
        )
    }
}