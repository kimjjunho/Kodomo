package com.example.data.repository

import com.example.data.remote.datasource.RemoteScheduleDataSource
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleResponseEntity
import com.example.domain.respository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteScheduleDataSource: RemoteScheduleDataSource
): ScheduleRepository {
    override suspend fun getSchedule(scheduleParam: ScheduleParam): ScheduleResponseEntity =
        remoteScheduleDataSource.getSchedule(scheduleParam)
}