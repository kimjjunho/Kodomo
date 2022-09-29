package com.example.domain.usecase.alarm

import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.entity.alarm.GetAlarmResponseEntity
import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
): UseCase<GetAlarmParam, GetAlarmResponseEntity>() {
    override suspend fun execute(data: GetAlarmParam): GetAlarmResponseEntity = alarmRepository.getAlarm(data)
}