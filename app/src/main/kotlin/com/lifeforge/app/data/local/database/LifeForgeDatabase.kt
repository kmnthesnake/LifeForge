package com.lifeforge.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lifeforge.app.data.local.dao.CharacterDao
import com.lifeforge.app.data.local.entity.CharacterEntity

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add new columns for family and education
        database.execSQL("ALTER TABLE characters ADD COLUMN motherName TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE characters ADD COLUMN fatherName TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE characters ADD COLUMN siblings INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE characters ADD COLUMN relationshipMother INTEGER NOT NULL DEFAULT 50")
        database.execSQL("ALTER TABLE characters ADD COLUMN relationshipFather INTEGER NOT NULL DEFAULT 50")
        database.execSQL("ALTER TABLE characters ADD COLUMN educationStage TEXT NOT NULL DEFAULT 'BABY'")
    }
}

@Database(
    entities = [CharacterEntity::class],
    version = 2,
    exportSchema = false
)
abstract class LifeForgeDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}