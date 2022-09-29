package com.example.domain.respository

import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity

interface AlarmRepository {
    suspend fun getAlarm(getAlarmRequestEntity: GetAlarmRequestEntity): GetAlarmResponseEntity

    suspend fun deleteAlarm(alarm_id: Long)
}