package com.example.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.local.entity.ScheduleRoomEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class ScheduleListTypeConverter(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun fromString(value: String): List<ScheduleRoomEntity.ScheduleRoom> ?{
        val listType = Types.newParameterizedType(
            List::class.java,
            ScheduleRoomEntity.ScheduleRoom::class.java
        )
        val adapter: JsonAdapter<List<ScheduleRoomEntity.ScheduleRoom>> =
            moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<ScheduleRoomEntity.ScheduleRoom>): String ?{
        val listType = Types.newParameterizedType(
            List::class.java,
            ScheduleRoomEntity.ScheduleRoom::class.java
        )
        val adapter: JsonAdapter<List<ScheduleRoomEntity.ScheduleRoom>> =
            moshi.adapter(listType)
        return adapter.toJson(type)
    }
}
