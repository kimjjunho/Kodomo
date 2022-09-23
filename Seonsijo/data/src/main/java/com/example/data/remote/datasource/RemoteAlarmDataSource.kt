package com.example.data.remote.datasource

import com.example.domain.entity.alarm.DeleteAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity

interface RemoteAlarmDataSource {
    suspend fun getAlarm(getAlarmRequestEntity: GetAlarmRequestEntity): GetAlarmResponseEntity

    suspend fun deleteAlarm(deleteAlarmRequestEntity: DeleteAlarmRequestEntity)
}