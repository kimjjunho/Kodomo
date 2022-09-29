package com.example.data.local.preference

import com.example.domain.entity.schedule.ScheduleEntity

interface SchedulePreference {

    suspend fun saveMonday(monday: String)
    suspend fun saveTuesday(tuesday: String)
    suspend fun saveWednesday(wednesday: String)
    suspend fun saveThursday(thursday: String)
    suspend fun saveFriday(friday: String)

    suspend fun fetchMonday(): String
    suspend fun fetchTuesday(): String
    suspend fun fetchWednesday(): String
    suspend fun fetchThursday(): String
    suspend fun fetchFriday(): String
}