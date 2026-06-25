package com.lifeforge.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lifeforge.app.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity): Long

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("SELECT * FROM characters ORDER BY updatedAt DESC LIMIT 1")
    fun getLastCharacter(): Flow<CharacterEntity?>

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun getCharacterById(characterId: String): Flow<CharacterEntity?>

    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}