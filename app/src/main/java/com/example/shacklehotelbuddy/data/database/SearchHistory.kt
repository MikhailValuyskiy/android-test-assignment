package com.example.shacklehotelbuddy.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val checkedInDate: String?,
    val checkedOutDate: String?,
    val adults: Int?,
    val children: Int?,
    val date: Long
)