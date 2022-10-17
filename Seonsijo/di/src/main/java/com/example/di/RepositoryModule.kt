package com.example.di

import com.example.data.repository.AlarmRepositoryImpl
import com.example.data.repository.ScheduleRepositoryImpl
import com.example.data.repository.SignUpRepositoryImpl
import com.example.domain.respository.AlarmRepository
import com.example.domain.respository.ScheduleRepository
import com.example.domain.respository.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository

    @Binds
    abstract fun bindAlarmRepository(
        alarmRepositoryImpl: AlarmRepositoryImpl
    ): AlarmRepository

    @Binds
    abstract fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}