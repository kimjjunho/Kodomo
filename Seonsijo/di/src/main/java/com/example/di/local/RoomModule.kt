package com.example.di.local

import android.content.Context
import androidx.room.Room
import com.example.data.local.converter.ScheduleListTypeConverter
import com.example.data.local.dao.ScheduleDao
import com.example.data.local.database.ScheduleDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideMainDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): ScheduleDatabase = Room
        .databaseBuilder(
            context,
            ScheduleDatabase::class.java,
            "SeonsijoDb"
        )
        .addTypeConverter(ScheduleListTypeConverter(moshi))
        .build()


    @Provides
    fun provideMainDao(
        scheduleDatabase: ScheduleDatabase
    ): ScheduleDao = scheduleDatabase.scheduleDao()
}
