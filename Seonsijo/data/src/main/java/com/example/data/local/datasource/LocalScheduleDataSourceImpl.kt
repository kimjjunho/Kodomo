package com.example.data.local.datasource

import com.example.data.local.dao.ScheduleDao
import com.example.data.local.entity.toEntity
import com.example.data.local.entity.toRoomEntity
import com.example.data.local.preference.SchedulePreference
import com.example.domain.entity.schedule.ScheduleEntity
import javax.inject.Inject

class LocalScheduleDataSourceImpl @Inject constructor(
    private val scheduleDao: ScheduleDao
): LocalScheduleDataSource {

    override suspend fun fetchSchedule(): ScheduleEntity =
        scheduleDao.getMain().toEntity()

    override suspend fun saveSchedule(scheduleEntity: ScheduleEntity) =
        scheduleDao.updateMain(scheduleEntity.toRoomEntity())
}