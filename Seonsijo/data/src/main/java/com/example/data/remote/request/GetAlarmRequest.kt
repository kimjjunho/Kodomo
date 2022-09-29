package com.example.data.remote.request

import com.example.domain.entity.alarm.GetAlarmParam

data class GetAlarmRequest(
    val grade: Int,
    val class_num: Int
)

fun GetAlarmParam.toRequest() = GetAlarmRequest(
    grade = grade,
    class_num = class_num
)
