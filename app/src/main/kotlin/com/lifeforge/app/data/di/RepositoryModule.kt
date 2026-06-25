package com.lifeforge.app.data.di

import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.data.repository.GameEventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCharacterRepository(
        characterRepository: CharacterRepository
    ): CharacterRepository = characterRepository

    @Singleton
    @Provides
    fun provideGameEventRepository(
        gameEventRepository: GameEventRepository
    ): GameEventRepository = gameEventRepository
}
