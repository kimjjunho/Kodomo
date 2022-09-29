package com.example.domain.usecase.alarm

import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
): UseCase<Long, Unit>() {
    override suspend fun execute(data: Long): Unit = alarmRepository.deleteAlarm(data)
}