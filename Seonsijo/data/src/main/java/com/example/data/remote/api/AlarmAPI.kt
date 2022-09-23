package com.example.data.remote.api

import com.example.data.remote.request.DeleteAlarmRequest
import com.example.data.remote.request.GetAlarmRequest
import com.example.data.remote.response.GetAlarmResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET

interface AlarmAPI {

    @GET("users/alarm")
    suspend fun getAlarm(
        @Body getAlarmRequest: GetAlarmRequest
    ): GetAlarmResponse

    @DELETE("users/alarm")
    suspend fun deleteAlarm(
        @Body deleteAlarmRequest: DeleteAlarmRequest
    )
}