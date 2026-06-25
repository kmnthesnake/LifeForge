package com.lifeforge.app.data.repository

import com.lifeforge.app.data.local.dao.GameEventDao
import com.lifeforge.app.data.local.entity.GameEventEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameEventRepository @Inject constructor(
    private val gameEventDao: GameEventDao
) {
    suspend fun addEvent(event: GameEventEntity) {
        gameEventDao.insert(event)
    }

    fun getEventsByCharacterId(characterId: Long): Flow<List<GameEventEntity>> {
        return gameEventDao.getEventsByCharacterId(characterId)
    }

    suspend fun deleteEventsByCharacterId(characterId: Long) {
        gameEventDao.deleteEventsByCharacterId(characterId)
    }
}
