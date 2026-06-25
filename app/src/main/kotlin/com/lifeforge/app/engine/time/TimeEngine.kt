package com.lifeforge.app.engine.time

import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.LifeEvent
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object TimeEngine {

    /**
     * Advances character's age by one year and generates life events.
     * Updates character statistics according to age-based rules.
     */
    fun ageUp(character: Character): Pair<Character, List<LifeEvent>> {
        val newAge = character.age + 1

        // Calculate stat changes
        val healthChange = Random.nextInt(-3, 3)  // -3 to +2
        val happinessChange = Random.nextInt(-5, 6)  // -5 to +5
        val fitnessChange = Random.nextInt(-3, 4)  // -3 to +3
        val looksChange = Random.nextInt(-2, 3)  // -2 to +2

        // Money increase only after age 18
        val moneyIncrease = if (newAge < 18) 0L else Random.nextLong(100, 501)

        // Intelligence increases based on age
        val intelligenceIncrease = when {
            newAge <= 5 -> 5
            newAge <= 12 -> 3
            newAge <= 18 -> 2
            else -> 0
        }

        // Apply changes with clamping
        val newHealth = clampStat(character.health + healthChange)
        val newHappiness = clampStat(character.happiness + happinessChange)
        val newFitness = clampStat(character.fitness + fitnessChange)
        val newLooks = clampStat(character.looks + looksChange)
        val newIntelligence = clampStat(character.intelligence + intelligenceIncrease)
        val newMoney = character.money + moneyIncrease

        // Create updated character
        val updatedCharacter = character.copy(
            age = newAge,
            health = newHealth,
            happiness = newHappiness,
            fitness = newFitness,
            looks = newLooks,
            intelligence = newIntelligence,
            money = newMoney,
            updatedAt = System.currentTimeMillis()
        )

        // Generate events for this year
        val events = LifeEventGenerator.generateEvents(newAge)

        return Pair(updatedCharacter, events)
    }

    private fun clampStat(value: Int): Int = min(100, max(0, value))
}
