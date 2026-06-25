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
    suspend fun saveCharacter(character: Character): Long {
        val updatedCharacter = character.copy(
            updatedAt = System.currentTimeMillis()
        )
        return characterDao.insert(updatedCharacter.toEntity())
    }

    suspend fun updateCharacter(character: Character) {
        val updatedCharacter = character.copy(
            updatedAt = System.currentTimeMillis()
        )
        characterDao.update(updatedCharacter.toEntity())
    }

    suspend fun deleteCharacter(character: Character) {
        characterDao.delete(character.toEntity())
    }

    fun getCharacterById(id: Long): Flow<Character?> {
        return characterDao.getCharacterById(id).map { it?.toDomain() }
    }

    fun getLastCharacter(): Flow<Character?> {
        return characterDao.getLastCharacter().map { it?.toDomain() }
    }

    fun getAllCharacters(): Flow<List<Character>> {
        return characterDao.getAllCharacters().map { characters ->
            characters.map { it.toDomain() }
        }
    }

    suspend fun deleteAll() {
        characterDao.deleteAll()
    }
}
