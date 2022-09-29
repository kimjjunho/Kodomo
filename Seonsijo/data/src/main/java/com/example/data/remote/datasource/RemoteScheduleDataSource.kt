package com.example.data.remote.datasource

import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleResponseEntity

interface RemoteScheduleDataSource {
    suspend fun getSchedule(scheduleParam: ScheduleParam): ScheduleResponseEntity
}