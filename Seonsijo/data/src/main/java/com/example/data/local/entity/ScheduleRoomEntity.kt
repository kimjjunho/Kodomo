package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.schedule.ScheduleEntity

@Entity(tableName = "schedule_room")
data class ScheduleRoomEntity(
    val grade: String,
    val class_num: String,
    val name: String,
    val gradations: String,
    val day_at: String,
    val week_of_day: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

fun ScheduleRoomEntity.toSchedule() =
     ScheduleEntity.Schedule(
         grade = this.grade,
         class_num = this.class_num,
         name = this.name,
         gradations = this.gradations,
         day_at = this.day_at,
         week_of_day = this.week_of_day
     )

fun List<ScheduleRoomEntity>.toEntity() =
    ScheduleEntity(
        mondayList = this.map { it.toSchedule() },
        tuesdayList = this.map { it.toSchedule() },
        wednesdayList = this.map { it.toSchedule() },
        thursdayList = this.map { it.toSchedule() },
        fridayList = this.map { it.toSchedule() },
    )

fun ScheduleEntity.Schedule.toRoomEntity() =
    ScheduleRoomEntity(
        grade = grade,
        class_num = class_num,
        name = name,
        gradations = gradations,
        day_at = day_at,
        week_of_day = week_of_day,
    )

fun ScheduleEntity.toRoomEntity() = run {
    this.mondayList.map { it.toRoomEntity() }
    this.tuesdayList.map { it.toRoomEntity() }
    this.wednesdayList.map { it.toRoomEntity() }
    this.thursdayList.map { it.toRoomEntity() }
    this.fridayList.map { it.toRoomEntity() }
}