package com.example.shacklehotelbuddy.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.domain.model.PropertyModel
import com.example.shacklehotelbuddy.domain.model.SearchModel
import com.example.shacklehotelbuddy.domain.usecase.GetPropertiesUseCase
import com.example.shacklehotelbuddy.domain.usecase.GetSearchHistoryUseCase
import com.example.shacklehotelbuddy.domain.usecase.SaveSearchHistoryUseCase
import com.example.shacklehotelbuddy.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPropertiesUseCase: GetPropertiesUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val saveSearchHistoryUseCase: SaveSearchHistoryUseCase
) : ViewModel() {

    val propertiesList get() = _propertiesResult.asStateFlow()
    private val _propertiesResult = MutableStateFlow(emptyList<PropertyModel>())

    val isInProgress get() = _isInProgress.asStateFlow()
    private val _isInProgress = MutableStateFlow(false)

    private val _searchHistory = MutableStateFlow(emptyList<SearchModel>())
    val searchHistory get() = _searchHistory.asStateFlow()

    init {
        getSearchList()
    }

    fun getProperties(
        checkInDate: String,
        checkOutDate: String,
        adultNumber: Int,
        childrenNumber: Int
    ) {
        viewModelScope.launch {
            _isInProgress.value = true
            getPropertiesUseCase.getProperties(
                checkedInDate = checkInDate,
                checkedOutDate = checkOutDate,
                adults = adultNumber,
                children = childrenNumber
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _propertiesResult.value = result.content
                        _isInProgress.value = false
                    }

                    is Result.Error -> {
                        _isInProgress.value = false
                    }
                }

            }

        }
    }

    private fun getSearchList() {
        viewModelScope.launch {
            getSearchHistoryUseCase.getSearchHistory().collect { result ->
                _searchHistory.value = result
            }
        }
    }

    fun saveSearchRequest(searchModel: SearchModel) {
        viewModelScope.launch {
            saveSearchHistoryUseCase.save(searchModel)
        }
    }

}