package com.example.data.local.preference

import android.util.Log
import com.example.data.util.SharedPreferenceHelper
import javax.inject.Inject

class SignInPreferenceImpl @Inject constructor(
    private val s: SharedPreferenceHelper
): SignInPreference {
    override suspend fun saveGradeNum(gradeNum: Int) =
        s.saveIntPreference(GRADE_NUM,gradeNum)


    override suspend fun fetchGradeNum(): Int =
        s.fetchIntPreference(GRADE_NUM,1)

    override suspend fun saveClassNum(classNum: Int) =
        s.saveIntPreference(CLASS_NUM,classNum)


    override suspend fun fetchClassNum(): Int =
        s.fetchIntPreference(CLASS_NUM,1)

    override suspend fun saveDeviceToken(device_token: String?) {
        s.saveStringPreference(DEVICE_TOKEN,device_token!!)
    }

    override suspend fun fetchDeviceToken(): String =
        s.fetchStringPreference(DEVICE_TOKEN)

    override suspend fun saveCheckDay(checkDay: Int) {
        s.saveIntPreference(CHECK_DAY, checkDay)
    }

    override suspend fun fetchCheckDay(): Int =
        s.fetchIntPreference(CHECK_DAY, 0)

    override suspend fun saveGradeClassCheck(gradeClassCheck: Boolean) =
        s.saveBooleanPreference(GRADE_CLASS_CHECK, gradeClassCheck)

    override suspend fun fetchGradeClassCheck(): Boolean =
        s.fetchBoolean(DEVICE_TOKEN)


    companion object Key{
        const val GRADE_NUM = "GRADE_NUM"
        const val CLASS_NUM = "CLASS_NUM"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
        const val GRADE_CLASS_CHECK = "GRADE_CLASS_CHECK"
        const val CHECK_DAY = "CHECK_DAY"
    }
}