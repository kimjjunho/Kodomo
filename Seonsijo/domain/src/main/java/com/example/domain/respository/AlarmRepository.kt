package com.example.domain.respository

import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmParam

interface AlarmRepository {
    suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmEntity

    suspend fun deleteAlarm(alarm_id: Long)
}