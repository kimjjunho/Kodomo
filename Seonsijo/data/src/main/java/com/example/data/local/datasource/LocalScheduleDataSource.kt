package com.example.data.local.datasource

import com.example.domain.entity.schedule.ScheduleEntity

interface LocalScheduleDataSource {

    suspend fun fetchSchedule(): ScheduleEntity

    suspend fun saveSchedule(scheduleEntity: ScheduleEntity)
}