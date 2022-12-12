package com.example.data.local.datasource

import com.example.data.local.preference.SchedulePreference
import com.example.domain.entity.schedule.ScheduleEntity
import javax.inject.Inject

class LocalScheduleDataSourceImpl @Inject constructor(
    private val schedulePreference: SchedulePreference
): LocalScheduleDataSource {

    override suspend fun fetchSchedule(): ScheduleEntity =
        with(schedulePreference){
            ScheduleEntity(
                mondayList = listOf(),
                tuesdayList = listOf(),
                wednesdayList = listOf(),
                thursdayList = listOf(),
                fridayList = listOf(),
            )
        }

    override suspend fun saveSchedule(scheduleEntity: ScheduleEntity) {
        with(schedulePreference){

        }
    }

}