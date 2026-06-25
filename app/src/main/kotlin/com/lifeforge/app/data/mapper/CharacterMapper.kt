package com.lifeforge.app.data.mapper

import com.lifeforge.app.data.local.entity.CharacterEntity
import com.lifeforge.app.domain.model.BodyType
import com.lifeforge.app.domain.model.Character
import com.lifeforge.app.domain.model.Gender

fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    gender = gender.name,
    country = country,
    city = city,
    bodyType = bodyType.name,
    health = health,
    happiness = happiness,
    intelligence = intelligence,
    looks = looks,
    fitness = fitness,
    money = money,
    alive = alive,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    gender = Gender.valueOf(gender),
    country = country,
    city = city,
    bodyType = BodyType.valueOf(bodyType),
    health = health,
    happiness = happiness,
    intelligence = intelligence,
    looks = looks,
    fitness = fitness,
    money = money,
    alive = alive,
    createdAt = createdAt,
    updatedAt = updatedAt
)