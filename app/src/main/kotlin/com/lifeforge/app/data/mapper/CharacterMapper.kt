package com.lifeforge.app.data.mapper

import com.lifeforge.app.data.local.entity.CharacterEntity
import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.CharacterStats
import com.lifeforge.app.domain.model.Gender

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    gender = Gender.valueOf(gender),
    country = country,
    city = city,
    bodyType = BodyType.valueOf(bodyType),
    age = age,
    stats = CharacterStats(
        health = health,
        fitness = fitness,
        looks = looks,
        intelligence = intelligence
    ),
    money = money,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    gender = gender.name,
    country = country,
    city = city,
    bodyType = bodyType.name,
    age = age,
    health = stats.health,
    fitness = stats.fitness,
    looks = stats.looks,
    intelligence = stats.intelligence,
    money = money,
    createdAt = createdAt,
    updatedAt = updatedAt
)
