package com.example.data.local.preference

import android.content.SharedPreferences
import com.example.domain.entity.alarm.GetAlarmEntity
import javax.inject.Inject

class AlarmPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
):AlarmPreference {
    override suspend fun saveAlarmId(alarm_id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTargetDate(target_date: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveIndex(index: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun saveSubject(subject: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveContent(content: String) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAlarmId(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTargetDate(): String {
        TODO("Not yet implemented")
    }

    override suspend fun fetchIndex(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSubject(): String {
        TODO("Not yet implemented")
    }

    override suspend fun fetchContent(): String {
        TODO("Not yet implemented")
    }

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun saveIntPreference(key: String, value: Int) =
        editPreference { it.putInt(key,value) }

    private fun fetchStringPreference(key: String) =
        sharedPreferences.getString(key, null) ?: ""
    //sharedPreferences.getString(key, "")

    private fun fetchIntPreference(key: String) =
        sharedPreferences.getInt(key,0)

    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }
}