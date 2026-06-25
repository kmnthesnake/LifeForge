package com.lifeforge.app.engine.di

import com.lifeforge.app.data.repository.CharacterRepository
import com.lifeforge.app.engine.simulation.SimulationEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EngineModule {
    @Singleton
    @Provides
    fun provideSimulationEngine(
        characterRepository: CharacterRepository
    ): SimulationEngine = SimulationEngine(characterRepository)
}
