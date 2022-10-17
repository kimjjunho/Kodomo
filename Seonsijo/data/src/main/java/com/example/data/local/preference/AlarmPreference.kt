package com.example.data.local.preference

interface AlarmPreference {

    suspend fun saveAlarmId(alarm_id: Int)
    suspend fun saveTargetDate(target_date: String)
    suspend fun saveIndex(index: Int)
    suspend fun saveSubject(subject: String)
    suspend fun saveContent(content: String)

    suspend fun fetchAlarmId(): Int
    suspend fun fetchTargetDate(): String
    suspend fun fetchIndex(): Int
    suspend fun fetchSubject(): String
    suspend fun fetchContent(): String
}