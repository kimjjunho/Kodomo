package com.example.data.remote.datasource

import com.example.data.remote.api.ScheduleAPI
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.data.sendHttpRequest
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.entity.schedule.ScheduleEntity
import javax.inject.Inject

class RemoteScheduleDataSourceImpl @Inject constructor(
    private val scheduleAPI: ScheduleAPI
): RemoteScheduleDataSource {
    override suspend fun getSchedule(scheduleParam: ScheduleParam): ScheduleEntity =
        sendHttpRequest(httpRequest = { scheduleAPI.getSchedule(scheduleParam.toRequest()).toEntity() })

}