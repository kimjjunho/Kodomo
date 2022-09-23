package com.example.domain.usecase.alarm

import com.example.domain.entity.alarm.DeleteAlarmRequestEntity
import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
): UseCase<DeleteAlarmRequestEntity, Unit>() {
    override suspend fun execute(data: DeleteAlarmRequestEntity): Unit = alarmRepository.deleteAlarm(data)
}