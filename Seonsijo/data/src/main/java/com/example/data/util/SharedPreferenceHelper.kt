package com.example.data.util

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }
    fun fetchStringPreference(key: String): String =
        sharedPreferences.getString(key, null) ?: ""

    fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    fun fetchLongPreference(key: String): Long =
        sharedPreferences.getLong(key, 0)

    fun saveLongPreference(key: String, value: Long) =
        editPreference { it.putLong(key, value) }

    fun fetchIntPreference(key: String, value: Int) =
        sharedPreferences.getInt(key,value)

    fun saveIntPreference(key: String, value: Int) =
        editPreference { it.putInt(key, value) }

    fun fetchBoolean(key: String, value: Boolean = false) =
        sharedPreferences.getBoolean(key, value)

    fun saveBooleanPreference(key: String, value: Boolean) =
        editPreference { it.putBoolean(key,value) }

    fun clearPreference(key: String) =
        editPreference { it.remove(key) }

}

