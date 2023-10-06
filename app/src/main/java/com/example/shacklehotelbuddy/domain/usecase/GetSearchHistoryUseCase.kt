package com.example.shacklehotelbuddy.domain.usecase

import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(private val searchHistoryRepository: SearchHistoryRepository) {

    fun getSearchHistory(): Flow<List<SearchModel>> = flow {
        emit(
            searchHistoryRepository.getSearchHistory()
        )
    }
}