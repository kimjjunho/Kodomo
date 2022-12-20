package com.example.di.local

import com.example.data.local.dao.ScheduleDao
import com.example.data.local.datasource.LocalScheduleDataSource
import com.example.data.local.datasource.LocalScheduleDataSourceImpl
import com.example.data.local.datasource.LocalSignInDataSource
import com.example.data.local.datasource.LocalSignInDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

//    @Binds
//    abstract fun bindLocalScheduleDataSource(
//        localScheduleDataSourceImpl: LocalScheduleDataSourceImpl
//    ): LocalScheduleDataSource

    @Binds
    abstract fun bindLocalSignInDataSource(
        localSignInDataSourceImpl: LocalSignInDataSourceImpl
    ): LocalSignInDataSource

}



@Module
@InstallIn(SingletonComponent::class)
object RoomDataSourceModule {

    @Singleton
    @Provides
    fun provideScheduleDataSource(
        scheduleDao: ScheduleDao,
    ): LocalScheduleDataSource = LocalScheduleDataSourceImpl(scheduleDao)
}