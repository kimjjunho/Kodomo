package com.example.data.remote.response

import com.example.domain.entity.schedule.ScheduleResponseEntity
import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("월") val monday: String,
    @SerializedName("화") val tuesday: String,
    @SerializedName("수") val wednesday: String,
    @SerializedName("목") val thursday: String,
    @SerializedName("금") val friday: String
)

fun ScheduleResponse.toEntity() = ScheduleResponseEntity(
    monday = monday,
    tuesday = tuesday,
    wednesday = wednesday,
    thursday = thursday,
    friday = friday
)
