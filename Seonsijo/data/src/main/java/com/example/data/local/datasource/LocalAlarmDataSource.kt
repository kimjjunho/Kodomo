package com.example.data.local.datasource

import com.example.domain.entity.alarm.GetAlarmEntity

interface LocalAlarmDataSource {
    suspend fun fetchAlarm(): GetAlarmEntity

    suspend fun saveAlarm(getAlarmEntity: GetAlarmEntity)
}