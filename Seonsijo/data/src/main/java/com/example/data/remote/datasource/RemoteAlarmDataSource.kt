package com.example.data.remote.datasource

import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmParam

interface RemoteAlarmDataSource {
    suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmEntity

    suspend fun deleteAlarm(alarm_id: Long)
}