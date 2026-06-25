package com.lifeforge.app.data.di

import com.lifeforge.app.data.repository.CharacterRepository
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
        repository: CharacterRepository
    ): CharacterRepository = repository
}
