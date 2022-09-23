package com.example.domain.respository

import com.example.domain.entity.schedule.ScheduleRequestEntity
import com.example.domain.entity.schedule.ScheduleResponseEntity

interface ScheduleRepository {
    suspend fun getSchedule(scheduleRequestEntity: ScheduleRequestEntity) : ScheduleResponseEntity
}