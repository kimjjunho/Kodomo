package com.example.data.repository

import com.example.data.remote.datasource.RemoteAlarmDataSource
import com.example.domain.entity.alarm.DeleteAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity
import com.example.domain.respository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val remoteAlarmDataSource: RemoteAlarmDataSource
): AlarmRepository{
    override suspend fun getAlarm(getAlarmRequestEntity: GetAlarmRequestEntity): GetAlarmResponseEntity =
        remoteAlarmDataSource.getAlarm(getAlarmRequestEntity)

    override suspend fun deleteAlarm(deleteAlarmRequestEntity: DeleteAlarmRequestEntity) =
        remoteAlarmDataSource.deleteAlarm(deleteAlarmRequestEntity)
}