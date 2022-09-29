package com.example.data.local.preference

import android.content.SharedPreferences
import javax.inject.Inject

class SchedulePreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): SchedulePreference {

    override suspend fun saveMonday(monday: String) =
        saveStringPreference(MONDAY,monday)

    override suspend fun saveTuesday(tuesday: String) =
        saveStringPreference(TUESDAY,tuesday)

    override suspend fun saveWednesday(wednesday: String) =
        saveStringPreference(WEDNESDAY, wednesday)

    override suspend fun saveThursday(thursday: String) =
        saveStringPreference(THURSDAY, thursday)

    override suspend fun saveFriday(friday: String)  =
        saveStringPreference(FRIDAY, friday)

    override suspend fun fetchMonday(): String =
        fetchStringPreference(MONDAY)

    override suspend fun fetchTuesday(): String =
        fetchStringPreference(TUESDAY)

    override suspend fun fetchWednesday(): String =
        fetchStringPreference(WEDNESDAY)

    override suspend fun fetchThursday(): String =
        fetchStringPreference(THURSDAY)

    override suspend fun fetchFriday(): String =
        fetchStringPreference(FRIDAY)

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun fetchStringPreference(key: String) =
        sharedPreferences.getString(key, null) ?: ""
        //sharedPreferences.getString(key, "")

    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val MONDAY = "monday"
        const val TUESDAY = "tuesday"
        const val WEDNESDAY = "wednesday"
        const val THURSDAY = "thursday"
        const val FRIDAY = "friday"
    }
}