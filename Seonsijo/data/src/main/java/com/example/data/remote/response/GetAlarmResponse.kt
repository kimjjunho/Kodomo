package com.example.data.remote.response

import com.example.domain.entity.alarm.GetAlarmResponseEntity
import com.example.domain.entity.alarm.GetAlarmResponseEntity.AlarmData

data class GetAlarmResponse(
    val alarm_list: List<AlarmData>
)

fun GetAlarmResponse.toEntity() = GetAlarmResponseEntity(
    alarm_list = alarm_list
)