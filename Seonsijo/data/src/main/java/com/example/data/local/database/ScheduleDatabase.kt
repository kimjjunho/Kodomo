package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.local.converter.ScheduleListTypeConverter
import com.example.data.local.dao.ScheduleDao
import com.example.data.local.entity.ScheduleRoomEntity

@Database(
    entities = [ScheduleRoomEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [ScheduleListTypeConverter::class])
abstract class ScheduleDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}
