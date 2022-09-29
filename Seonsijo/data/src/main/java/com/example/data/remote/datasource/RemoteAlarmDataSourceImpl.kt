package com.example.data.remote.datasource

import com.example.data.remote.api.AlarmAPI
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.data.sendHttpRequest
import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmParam
import javax.inject.Inject

class RemoteAlarmDataSourceImpl @Inject constructor(
    private val alarmAPI: AlarmAPI
): RemoteAlarmDataSource {
    override suspend fun getAlarm(getAlarmParam: GetAlarmParam): GetAlarmEntity =
        sendHttpRequest(httpRequest = { alarmAPI.getAlarm(getAlarmParam.toRequest()).toEntity() })

    override suspend fun deleteAlarm(alarm_id: Long) =
        sendHttpRequest(httpRequest = { alarmAPI.deleteAlarm(alarm_id)})
}