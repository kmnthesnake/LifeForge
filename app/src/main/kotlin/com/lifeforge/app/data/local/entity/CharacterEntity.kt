package com.lifeforge.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val gender: String,
    val country: String,
    val city: String,
    val bodyType: String,
    val age: Int,
    val health: Int,
    val fitness: Int,
    val looks: Int,
    val intelligence: Int,
    val money: Long,
    val createdAt: Long,
    val updatedAt: Long
)
