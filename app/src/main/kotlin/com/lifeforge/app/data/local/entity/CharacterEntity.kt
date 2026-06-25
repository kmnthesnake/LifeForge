package com.lifeforge.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val dbId: Long = 0,
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val country: String,
    val city: String,
    val bodyType: String,
    val health: Int,
    val happiness: Int,
    val intelligence: Int,
    val looks: Int,
    val fitness: Int,
    val money: Long,
    val alive: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)