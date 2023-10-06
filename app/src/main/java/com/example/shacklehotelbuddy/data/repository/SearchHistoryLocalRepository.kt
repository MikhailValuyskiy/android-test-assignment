package com.example.shacklehotelbuddy.data.repository

import com.example.shacklehotelbuddy.data.database.SearchHistoryDao
import com.example.shacklehotelbuddy.data.mapper.SearchHistoryMapper
import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.domain.repository.SearchHistoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryLocalRepository @Inject constructor(private val dao: SearchHistoryDao) :
    SearchHistoryRepository {

    override suspend fun getSearchHistory(): List<SearchModel> {
        return dao.getSearchHistory().map {
            SearchHistoryMapper.mapFromDbo(it)
        }
    }

    override suspend fun saveSearchItem(item: SearchModel) {
        dao.saveSearchItem(SearchHistoryMapper.mapToDbo(item))
    }
}