package com.lifeforge.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lifeforge.app.data.local.dao.CharacterDao
import com.lifeforge.app.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LifeForgeDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}