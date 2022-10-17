package com.example.domain.usecase.alarm

import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.respository.AlarmRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

//class GetAutoAlarmUseCase @Inject constructor(
//    private val alarmRepository: AlarmRepository
//): UseCase<Unit, GetAlarmEntity>(){
//    override suspend fun execute(data: Unit): GetAlarmEntity =
//        alarmRepository.getAutoAlarm()
//}