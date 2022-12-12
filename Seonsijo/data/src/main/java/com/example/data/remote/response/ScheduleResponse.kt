package com.example.data.remote.response

import com.example.domain.entity.schedule.ScheduleEntity
import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("0") val mondayList: List<Schedule>,
    @SerializedName("1") val tuesdayList: List<Schedule>,
    @SerializedName("2") val wednesdayList: List<Schedule>,
    @SerializedName("3") val thursdayList: List<Schedule>,
    @SerializedName("4") val fridayList: List<Schedule>,
) {
    data class Schedule(
        @SerializedName("schedule_id") val schedule_id: String,
        @SerializedName("grade") val grade: String,
        @SerializedName("room") val room: String,
        @SerializedName("subject") val subject: String,
        @SerializedName("sequence") val sequence: String,
        @SerializedName("day") val day: String,
        @SerializedName("week_of_day") val week_of_day: String,
    )
}

fun ScheduleResponse.toEntity() = ScheduleEntity(
    mondayList = mondayList.map { it.toEntity() },
    tuesdayList = tuesdayList.map { it.toEntity() },
    wednesdayList = wednesdayList.map { it.toEntity() },
    thursdayList = thursdayList.map { it.toEntity() },
    fridayList = fridayList.map { it.toEntity() },
)

fun ScheduleResponse.Schedule.toEntity() = ScheduleEntity.Schedule(
    schedule_id = schedule_id,
    grade = grade,
    room = room,
    subject = subject,
    sequence = sequence,
    day = day,
    week_of_day = week_of_day,
)
