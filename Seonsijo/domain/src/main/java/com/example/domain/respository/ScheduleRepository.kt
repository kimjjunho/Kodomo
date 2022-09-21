package com.example.domain.respository

import com.example.domain.entity.ScheduleRequestEntity
import com.example.domain.entity.ScheduleResponseEntity

interface ScheduleRepository {
    suspend fun getSchedule(scheduleRequestEntity: ScheduleRequestEntity) : ScheduleResponseEntity
}