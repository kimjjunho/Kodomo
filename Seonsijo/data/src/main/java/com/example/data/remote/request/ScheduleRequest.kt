package com.example.data.remote.request

import com.example.domain.entity.schedule.ScheduleRequestEntity

data class ScheduleRequest(
    val grade: Int,
    val class_num: Int,
    val date: String
)

fun ScheduleRequestEntity.toRequest() = ScheduleRequest(
    grade = grade,
    class_num = class_num,
    date = date
)
