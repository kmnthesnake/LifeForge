package com.lifeforge.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lifeforge.app.data.local.dao.CharacterDao
import com.lifeforge.app.data.local.dao.GameEventDao
import com.lifeforge.app.data.local.entity.CharacterEntity
import com.lifeforge.app.data.local.entity.GameEventEntity

@Database(
    entities = [CharacterEntity::class, GameEventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LifeForgeDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun gameEventDao(): GameEventDao
}
