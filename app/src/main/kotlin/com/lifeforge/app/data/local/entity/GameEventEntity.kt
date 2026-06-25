package com.lifeforge.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_events")
data class GameEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val characterId: Long,
    val title: String,
    val description: String,
    val age: Int,
    val healthChange: Int = 0,
    val fitnessChange: Int = 0,
    val looksChange: Int = 0,
    val intelligenceChange: Int = 0,
    val moneyChange: Long = 0L,
    val timestamp: Long
)
