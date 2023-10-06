package com.example.shacklehotelbuddy.network

import com.example.shacklehotelbuddy.data.dto.PropertiesDetailsRequest
import com.example.shacklehotelbuddy.data.dto.PropertiesListRequest
import com.example.shacklehotelbuddy.data.dto.PropertiesListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PropertiesApi {

    @POST("properties/v2/list")
    suspend fun getPropertiesList(
        @Body requestBody:  PropertiesListRequest
    ): PropertiesListResponse

    @POST("properties/v2/detail")
    suspend fun getPropertiesDetails(
        @Body propertiesDetailsRequestEntity: PropertiesDetailsRequest
    ): String
}