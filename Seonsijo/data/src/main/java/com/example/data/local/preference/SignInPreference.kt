package com.example.data.local.preference

interface SignInPreference {

    suspend fun saveGradeNum(gradeNum: Int)

    suspend fun fetchGradeNum(): Int

    suspend fun saveClassNum(classNum: Int)

    suspend fun fetchClassNum(): Int

    suspend fun saveDeviceToken(device_token: String?)

    suspend fun fetchDeviceToken(): String?

    suspend fun saveGradeClassCheck(gradeClassCheck: Boolean)

    suspend fun fetchGradeClassCheck(): Boolean

    suspend fun saveCheckDay(checkDay: Int)

    suspend fun fetchCheckDay(): Int
}