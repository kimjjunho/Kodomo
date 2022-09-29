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
                monday = fetchMonday(),
                tuesday = fetchTuesday(),
                wednesday = fetchWednesday(),
                thursday = fetchThursday(),
                friday = fetchFriday()
            )
        }

    override suspend fun saveSchedule(scheduleEntity: ScheduleEntity) {
        with(schedulePreference){
            saveMonday(scheduleEntity.monday)
            saveTuesday(scheduleEntity.tuesday)
            saveWednesday(scheduleEntity.wednesday)
            saveThursday(scheduleEntity.thursday)
            saveFriday(scheduleEntity.friday)
        }
    }

}