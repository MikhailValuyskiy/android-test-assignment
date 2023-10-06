package com.example.shacklehotelbuddy.domain.usecase

import com.example.shacklehotelbuddy.domain.model.PropertyModel
import com.example.shacklehotelbuddy.domain.repository.PropertiesRepository
import com.example.shacklehotelbuddy.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPropertiesUseCase @Inject constructor(
    private val propertiesRepository: PropertiesRepository
) {
    fun getProperties(
        checkedInDate: String,
        checkedOutDate: String,
        adults: Int,
        children: Int
    ): Flow<Result<List<PropertyModel>>> = flow {
        emit(
            propertiesRepository.getPropertiesList(checkedInDate, checkedOutDate, adults, children)
        )
    }

}