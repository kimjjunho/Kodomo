package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.local.entity.ScheduleRoomEntity

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule_room")
    suspend fun getMain(): List<ScheduleRoomEntity>

    @Query("DELETE FROM schedule_room")
    suspend fun deleteSchedule()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMain(mainRoomEntity: List<ScheduleRoomEntity>)

    @Transaction
    suspend fun updateMain(repo: List<ScheduleRoomEntity>){
        deleteSchedule()
        insertMain(repo)
    }
}