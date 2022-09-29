package com.example.domain.respository

import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.entity.alarm.GetAlarmResponseEntity

interface AlarmRepository {
    suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmResponseEntity

    suspend fun deleteAlarm(alarm_id: Long)
}