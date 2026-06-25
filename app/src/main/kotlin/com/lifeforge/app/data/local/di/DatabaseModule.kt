package com.lifeforge.app.data.local.di

import android.content.Context
import androidx.room.Room
import com.lifeforge.app.data.local.database.LifeForgeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): LifeForgeDatabase = Room.databaseBuilder(
        context,
        LifeForgeDatabase::class.java,
        "lifeforge_database"
    ).build()

    @Singleton
    @Provides
    fun provideCharacterDao(database: LifeForgeDatabase) = database.characterDao()
}
