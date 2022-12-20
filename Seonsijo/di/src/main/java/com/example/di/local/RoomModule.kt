package com.example.di.local

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.ScheduleDao
import com.example.data.local.database.ScheduleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideMainDatabase(
        @ApplicationContext context: Context
    ): ScheduleDatabase = Room
        .databaseBuilder(
            context,
            ScheduleDatabase::class.java,
            "schedule"
        ).build()

    @Provides
    fun provideMainDao(
        scheduleDatabase: ScheduleDatabase
    ): ScheduleDao = scheduleDatabase.scheduleDao()
}