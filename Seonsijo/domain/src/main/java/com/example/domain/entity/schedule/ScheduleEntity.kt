package com.example.domain.entity.schedule

data class ScheduleEntity(
    val mondayList: List<Schedule>,
    val tuesdayList: List<Schedule>,
    val wednesdayList: List<Schedule>,
    val thursdayList: List<Schedule>,
    val fridayList: List<Schedule>
) {
    data class Schedule(
        val grade: String,
        val room: String,
        val subject: String,
        val sequence: String,
        val day: String,
        val week_of_day: String,
    )
}
