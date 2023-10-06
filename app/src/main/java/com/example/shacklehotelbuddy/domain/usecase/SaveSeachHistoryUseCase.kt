package com.example.shacklehotelbuddy.domain.usecase

import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.domain.repository.SearchHistoryRepository
import javax.inject.Inject

class SaveSearchHistoryUseCase @Inject constructor(
    private val repository: SearchHistoryRepository
) {

    suspend fun save(model: SearchModel) = repository.saveSearchItem(model)
}