package com.lifeforge.app.domain.model

import java.util.UUID

data class Character(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val country: String,
    val city: String,
    val bodyType: BodyType,
    val health: Int,
    val happiness: Int,
    val intelligence: Int,
    val looks: Int,
    val fitness: Int,
    val money: Long,
    val alive: Boolean = true,
    // Family
    val motherName: String,
    val fatherName: String,
    val siblings: Int,
    val relationshipMother: Int,
    val relationshipFather: Int,
    // Education
    val educationStage: EducationStage,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    init {
        require(health in 0..100) { "Health must be between 0 and 100" }
        require(happiness in 0..100) { "Happiness must be between 0 and 100" }
        require(intelligence in 0..100) { "Intelligence must be between 0 and 100" }
        require(looks in 0..100) { "Looks must be between 0 and 100" }
        require(fitness in 0..100) { "Fitness must be between 0 and 100" }
        require(money >= 0) { "Money cannot be negative" }
        require(age >= 0) { "Age cannot be negative" }
        require(siblings in 0..4) { "Siblings must be between 0 and 4" }
        require(relationshipMother in 0..100) { "Mother relationship must be between 0 and 100" }
        require(relationshipFather in 0..100) { "Father relationship must be between 0 and 100" }
    }

    fun getFullName(): String = "$firstName $lastName"
}