package com.lifeforge.app.domain.model

import com.lifeforge.app.domain.model.CharacterStats
import java.time.LocalDateTime

data class Character(
    val id: Long = 0L,
    val name: String,
    val gender: Gender,
    val country: String,
    val city: String,
    val bodyType: BodyType,
    val age: Int,
    val stats: CharacterStats,
    val money: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class Gender {
    MALE,
    FEMALE
}

enum class BodyType {
    SLENDER,
    LEAN,
    PETITE,
    AVERAGE,
    CURVY,
    ATHLETIC,
    STOCKY,
    HEAVYSET,
    OVERWEIGHT
}

data class CharacterStats(
    val health: Int,
    val fitness: Int,
    val looks: Int,
    val intelligence: Int
) {
    init {
        require(health in 1..100) { "Health must be between 1 and 100" }
        require(fitness in 1..100) { "Fitness must be between 1 and 100" }
        require(looks in 1..100) { "Looks must be between 1 and 100" }
        require(intelligence in 1..100) { "Intelligence must be between 1 and 100" }
    }
}
