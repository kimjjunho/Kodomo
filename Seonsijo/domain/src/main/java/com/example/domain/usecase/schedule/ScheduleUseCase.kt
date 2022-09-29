package com.example.domain.usecase.schedule

import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.respository.ScheduleRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class ScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): UseCase<ScheduleParam, ScheduleEntity>() {
    override suspend fun execute(data: ScheduleParam) = scheduleRepository.getSchedule(data)
}