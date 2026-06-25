package com.lifeforge.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lifeforge.app.data.local.entity.GameEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: GameEventEntity)

    @Query("SELECT * FROM game_events WHERE characterId = :characterId ORDER BY timestamp DESC")
    fun getEventsByCharacterId(characterId: Long): Flow<List<GameEventEntity>>

    @Query("DELETE FROM game_events WHERE characterId = :characterId")
    suspend fun deleteEventsByCharacterId(characterId: Long)
}
