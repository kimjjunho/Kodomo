package com.example.data.remote.request

import com.example.domain.entity.alarm.DeleteAlarmRequestEntity

data class DeleteAlarmRequest(
    val alarm_id: Long
)

fun DeleteAlarmRequestEntity.toRequest() = DeleteAlarmRequest(
    alarm_id = alarm_id
)
