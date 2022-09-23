package com.example.domain.usecase.schedule

import com.example.domain.entity.schedule.ScheduleRequestEntity
import com.example.domain.entity.schedule.ScheduleResponseEntity
import com.example.domain.respository.ScheduleRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class ScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): UseCase<ScheduleRequestEntity, ScheduleResponseEntity>() {
    override suspend fun execute(data: ScheduleRequestEntity) = scheduleRepository.getSchedule(data)
}