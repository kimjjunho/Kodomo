package com.example.domain.entity.alarm

data class GetAlarmResponseEntity(
    val alarm_list: List<AlarmData>
) {
    data class AlarmData(
        val alarm_id: Int,
        val target_date: String,
        val index: Int,
        val subject: String,
        val content: String
    )
}
