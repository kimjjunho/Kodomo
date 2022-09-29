package com.example.data.remote.datasource

import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.entity.alarm.GetAlarmResponseEntity

interface RemoteAlarmDataSource {
    suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmResponseEntity

    suspend fun deleteAlarm(alarm_id: Long)
}