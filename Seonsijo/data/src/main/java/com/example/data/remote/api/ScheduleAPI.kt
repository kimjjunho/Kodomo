package com.example.data.remote.api

import com.example.data.remote.response.ScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleAPI {

    @GET("/schedules")
    suspend fun getSchedule(
        @Query("grade") grade: String,
        @Query("classNum") room: String,
        @Query("startAt") startAt: String,
        @Query("endAt") endAt: String,
    ): ScheduleResponse
}