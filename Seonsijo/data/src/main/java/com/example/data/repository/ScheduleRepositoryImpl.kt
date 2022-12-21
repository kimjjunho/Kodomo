package com.example.data.repository

import com.example.data.local.datasource.LocalScheduleDataSource
import com.example.data.remote.datasource.RemoteScheduleDataSource
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.respository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteScheduleDataSource: RemoteScheduleDataSource,
    private val localScheduleDataSource: LocalScheduleDataSource
): ScheduleRepository {

    override suspend fun getSchedule(scheduleParam: ScheduleParam): ScheduleEntity {
        val data = remoteScheduleDataSource.getSchedule(scheduleParam)
        localScheduleDataSource.saveSchedule(data)
        return data
    }

    override suspend fun autoSchedule(): ScheduleEntity =
        localScheduleDataSource.fetchSchedule()
}