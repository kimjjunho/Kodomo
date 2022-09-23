package com.example.data.remote.datasource

import com.example.data.remote.api.AlarmAPI
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.data.sendHttpRequest
import com.example.domain.entity.alarm.DeleteAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity
import javax.inject.Inject

class RemoteAlarmDataSourceImpl @Inject constructor(
    private val alarmAPI: AlarmAPI
): RemoteAlarmDataSource {
    override suspend fun getAlarm(getAlarmRequestEntity: GetAlarmRequestEntity): GetAlarmResponseEntity =
        sendHttpRequest(httpRequest = { alarmAPI.getAlarm(getAlarmRequestEntity.toRequest()).toEntity() })

    override suspend fun deleteAlarm(deleteAlarmRequestEntity: DeleteAlarmRequestEntity) =
        sendHttpRequest(httpRequest = { alarmAPI.deleteAlarm(deleteAlarmRequestEntity.toRequest())})
}