package com.example.shacklehotelbuddy.domain.repository

import com.example.shacklehotelbuddy.domain.model.SearchModel

interface SearchHistoryRepository {

    suspend fun getSearchHistory(): List<SearchModel>

    suspend fun saveSearchItem(item: SearchModel)

}