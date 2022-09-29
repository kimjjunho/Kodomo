package com.example.data.remote.response

import com.example.domain.entity.alarm.GetAlarmEntity

data class GetAlarmResponse(
    val alarm_list: List<AlarmData>
    ){
    data class AlarmData(
        val alarm_id: Int,
        val target_date: String,
        val index: Int,
        val subject: String,
        val content: String
    )
}

fun GetAlarmResponse.toEntity() = GetAlarmEntity(
    alarm_list = alarm_list.map { it.toList() }
)

fun GetAlarmResponse.AlarmData.toList() = GetAlarmEntity.AlarmData(
    alarm_id = alarm_id,
    target_date = target_date,
    index = index,
    subject = subject,
    content = content
)