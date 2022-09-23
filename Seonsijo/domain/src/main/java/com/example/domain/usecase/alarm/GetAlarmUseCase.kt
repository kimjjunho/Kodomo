package com.example.domain.usecase.alarm

import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity
import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
): UseCase<GetAlarmRequestEntity, GetAlarmResponseEntity>() {
    override suspend fun execute(data: GetAlarmRequestEntity): GetAlarmResponseEntity = alarmRepository.getAlarm(data)
}