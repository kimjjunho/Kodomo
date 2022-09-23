package com.example.di

import com.example.data.remote.datasource.RemoteAlarmDataSource
import com.example.data.remote.datasource.RemoteAlarmDataSourceImpl
import com.example.data.remote.datasource.RemoteScheduleDataSourceImpl
import com.example.data.remote.datasource.RemoteScheduleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindRemoteScheduleDataModule(
        remoteScheduleDataSourceImpl: RemoteScheduleDataSourceImpl
    ): RemoteScheduleDataSource

    @Binds
    abstract fun bindRemoteAlarmDataModule(
        remoteAlarmDataSourceImpl: RemoteAlarmDataSourceImpl
    ): RemoteAlarmDataSource
}