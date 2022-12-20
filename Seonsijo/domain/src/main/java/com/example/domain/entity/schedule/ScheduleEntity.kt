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
        val class_num: String,
        val name: String,
        val gradations: String,
        val day_at: String,
        val week_of_day: String,
    )
}
