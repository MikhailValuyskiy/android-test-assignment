package com.example.shacklehotelbuddy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SearchHistory::class], version = 1)
abstract class ShackleDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        const val DATABASE_NAME = "shackle_db"
    }
}