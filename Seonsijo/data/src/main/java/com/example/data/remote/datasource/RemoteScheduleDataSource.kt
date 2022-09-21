package com.example.data.remote.datasource

import com.example.domain.entity.ScheduleRequestEntity
import com.example.domain.entity.ScheduleResponseEntity

interface RemoteScheduleDataSource {
    suspend fun getSchedule(scheduleRequestEntity: ScheduleRequestEntity): ScheduleResponseEntity
}