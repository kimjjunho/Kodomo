package com.example.domain.usecase.schedule

import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.respository.ScheduleRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class AutoScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): UseCase<Unit, ScheduleEntity>() {
    override suspend fun execute(data: Unit): ScheduleEntity =
        scheduleRepository.autoSchedule()
}