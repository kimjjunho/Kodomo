package com.example.domain.respository

import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleResponseEntity

interface ScheduleRepository {
    suspend fun getSchedule(scheduleParam: ScheduleParam) : ScheduleResponseEntity
}