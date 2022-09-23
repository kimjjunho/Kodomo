package com.example.data.remote.datasource

import com.example.domain.entity.schedule.ScheduleRequestEntity
import com.example.domain.entity.schedule.ScheduleResponseEntity

interface RemoteScheduleDataSource {
    suspend fun getSchedule(scheduleRequestEntity: ScheduleRequestEntity): ScheduleResponseEntity
}