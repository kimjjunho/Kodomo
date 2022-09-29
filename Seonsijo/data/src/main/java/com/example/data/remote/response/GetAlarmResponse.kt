package com.example.data.remote.response

import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmEntity.AlarmData

data class GetAlarmResponse(
    val alarm_list: List<AlarmData>
)

fun GetAlarmResponse.toEntity() = GetAlarmEntity(
    alarm_list = alarm_list
)