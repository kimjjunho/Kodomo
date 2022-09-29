package com.example.domain.usecase.alarm

import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class GetAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
): UseCase<GetAlarmParam, GetAlarmEntity>() {
    override suspend fun execute(data: GetAlarmParam): GetAlarmEntity = alarmRepository.getAlarm(data)
}