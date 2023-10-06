package com.example.shacklehotelbuddy.domain.repository

import com.example.shacklehotelbuddy.domain.model.PropertyModel
import com.example.shacklehotelbuddy.network.Result

interface PropertiesRepository {

    suspend fun getPropertiesList(
        checkInDate: String,
        checkOutDate: String,
        adult: Int,
        children: Int
    ): Result<List<PropertyModel>>

}