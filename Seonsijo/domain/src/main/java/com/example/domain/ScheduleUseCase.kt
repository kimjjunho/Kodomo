package com.example.domain

import com.example.domain.entity.ScheduleRequestEntity
import com.example.domain.entity.ScheduleResponseEntity
import com.example.domain.respository.ScheduleRepository
import javax.inject.Inject

class ScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): UseCase<ScheduleRequestEntity, ScheduleResponseEntity>() {
    override suspend fun execute(data: ScheduleRequestEntity) = scheduleRepository.getSchedule(data)
}