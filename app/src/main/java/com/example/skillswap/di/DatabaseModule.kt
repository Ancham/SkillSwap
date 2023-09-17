package com.example.skillswap.di

import android.content.Context
import com.example.skillswap.data.AppDatabase
import com.example.skillswap.data.SkillDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Provides
    fun provideSkillDao(appDatabase: AppDatabase): SkillDao {
        return appDatabase.skillDao()
    }
}