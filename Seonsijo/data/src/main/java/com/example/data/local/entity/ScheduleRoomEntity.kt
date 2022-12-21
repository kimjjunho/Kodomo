package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.schedule.ScheduleEntity

@Entity(tableName = "schedule_room")
data class ScheduleRoomEntity(
   val mondayList: List<ScheduleRoom>,
   val tuesdayList: List<ScheduleRoom>,
   val wednesdayList: List<ScheduleRoom>,
   val thursdayList: List<ScheduleRoom>,
   val fridayList: List<ScheduleRoom>,
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    data class ScheduleRoom(
        val grade: String,
        val class_num: String,
        val name: String,
        val gradations: String,
        val day_at: String,
        val week_of_day: String
    )
}


fun ScheduleRoomEntity.ScheduleRoom.toSchedule() =
     ScheduleEntity.Schedule(
         grade = this.grade,
         class_num = this.class_num,
         name = this.name,
         gradations = this.gradations,
         day_at = this.day_at,
         week_of_day = this.week_of_day,
     )

fun ScheduleRoomEntity.toEntity() =
    ScheduleEntity(
        mondayList = this.mondayList.map { it.toSchedule() },
        tuesdayList = this.tuesdayList.map { it.toSchedule() },
        wednesdayList = this.wednesdayList.map { it.toSchedule() },
        thursdayList = this.thursdayList.map { it.toSchedule() },
        fridayList = this.fridayList.map { it.toSchedule() },
    )

fun ScheduleEntity.Schedule.toRoom() =
    ScheduleRoomEntity.ScheduleRoom(
        grade = this.grade,
        class_num = this.class_num,
        name = this.name,
        gradations = this.gradations,
        day_at = this.day_at,
        week_of_day = this.week_of_day,
    )

fun ScheduleEntity.toRoomEntity() =
    ScheduleRoomEntity(
        this.mondayList.map { it.toRoom() },
        this.tuesdayList.map { it.toRoom() },
        this.wednesdayList.map { it.toRoom() },
        this.thursdayList.map { it.toRoom() },
        this.fridayList.map { it.toRoom() },
    )
