package com.example.di

import com.example.data.local.datasource.LocalScheduleDataSource
import com.example.data.local.datasource.LocalScheduleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindLocalScheduleDataSource(
        localScheduleDataSourceImpl: LocalScheduleDataSourceImpl
    ):LocalScheduleDataSource
}