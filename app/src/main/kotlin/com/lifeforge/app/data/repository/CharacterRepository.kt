package com.lifeforge.app.data.repository

import com.lifeforge.app.data.local.dao.CharacterDao
import com.lifeforge.app.data.mapper.toDomain
import com.lifeforge.app.data.mapper.toEntity
import com.lifeforge.app.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun saveCharacter(character: Character) {
        characterDao.insert(character.toEntity())
    }

    suspend fun updateCharacter(character: Character) {
        characterDao.update(character.toEntity())
    }

    suspend fun deleteCharacter(character: Character) {
        characterDao.delete(character.toEntity())
    }

    fun getLastCharacter(): Flow<Character?> {
        return characterDao.getLastCharacter().map { it?.toDomain() }
    }

    fun getCharacterById(characterId: String): Flow<Character?> {
        return characterDao.getCharacterById(characterId).map { it?.toDomain() }
    }

    suspend fun deleteAll() {
        characterDao.deleteAll()
    }
}