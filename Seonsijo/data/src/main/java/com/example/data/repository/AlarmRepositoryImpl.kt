package com.example.data.repository

import com.example.data.remote.datasource.RemoteAlarmDataSource
import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.entity.alarm.GetAlarmResponseEntity
import com.example.domain.respository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val remoteAlarmDataSource: RemoteAlarmDataSource
): AlarmRepository{
    override suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmResponseEntity =
        remoteAlarmDataSource.getAlarm(getAlarmParam)

    override suspend fun deleteAlarm(alarm_id: Long) =
        remoteAlarmDataSource.deleteAlarm(alarm_id)
}