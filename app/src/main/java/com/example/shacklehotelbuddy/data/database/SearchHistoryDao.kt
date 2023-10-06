package com.example.shacklehotelbuddy.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM history")
    suspend fun getSearchHistory(): List<SearchHistory>

    @Insert
    suspend fun saveSearchItem(entity: SearchHistory)

    @Delete
    suspend fun deleteSearchItem(entity: SearchHistory)
}