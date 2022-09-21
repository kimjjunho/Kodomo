package com.example.data.remote.api

import com.example.data.remote.request.ScheduleRequest
import com.example.data.remote.response.ScheduleResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface ScheduleAPI {

    @GET("users/schedule")
    suspend fun getSchedule(
        @Body scheduleRequestRequest: ScheduleRequest
    ): ScheduleResponse
}