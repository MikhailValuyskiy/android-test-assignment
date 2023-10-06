package com.example.shacklehotelbuddy.data.repository

import com.example.shacklehotelbuddy.data.dto.CheckDateDto
import com.example.shacklehotelbuddy.data.dto.ChildrenDto
import com.example.shacklehotelbuddy.data.dto.DEFAULT_CHILDREN_AGE
import com.example.shacklehotelbuddy.data.dto.DEFAULT_CURRENCY
import com.example.shacklehotelbuddy.data.dto.DEFAULT_EAP_ID
import com.example.shacklehotelbuddy.data.dto.DEFAULT_LOCALE
import com.example.shacklehotelbuddy.data.dto.DEFAULT_MAX_PRICE
import com.example.shacklehotelbuddy.data.dto.DEFAULT_MIN_PRICE
import com.example.shacklehotelbuddy.data.dto.DEFAULT_SORT
import com.example.shacklehotelbuddy.data.dto.DEFAULT_REGION_ID
import com.example.shacklehotelbuddy.data.dto.DEFAULT_RESULT_SIZE
import com.example.shacklehotelbuddy.data.dto.DEFAULT_RESULT_STARTING_INDEX
import com.example.shacklehotelbuddy.data.dto.DEFAULT_SITE_ID
import com.example.shacklehotelbuddy.data.dto.DestinationDto
import com.example.shacklehotelbuddy.data.dto.FiltersDto
import com.example.shacklehotelbuddy.data.dto.PriceDto
import com.example.shacklehotelbuddy.data.dto.PropertiesListRequest
import com.example.shacklehotelbuddy.data.dto.RoomDto
import com.example.shacklehotelbuddy.data.mapper.PropertyMapper
import com.example.shacklehotelbuddy.domain.model.PropertyModel
import com.example.shacklehotelbuddy.domain.repository.PropertiesRepository
import com.example.shacklehotelbuddy.network.NetworkErrorHandler
import com.example.shacklehotelbuddy.network.PropertiesApi
import com.example.shacklehotelbuddy.network.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PropertiesRemoteRepository @Inject constructor(
    private val api: PropertiesApi,
    private val errorHandler: NetworkErrorHandler
) :
    PropertiesRepository {

    override suspend fun getPropertiesList(
        checkInDate: String,
        checkOutDate: String,
        adult: Int,
        children: Int
    ): Result<List<PropertyModel>> = errorHandler.handle {

        val checkIn = getDate(checkInDate)
        val checkOut = getDate(checkOutDate)

        val childList = mutableListOf<ChildrenDto>()
        for (i in 1..children) {
            childList.add(ChildrenDto(DEFAULT_CHILDREN_AGE))
        }


        val request = PropertiesListRequest(
            currency = DEFAULT_CURRENCY,
            eaPid = DEFAULT_EAP_ID,
            locale = DEFAULT_LOCALE,
            siteId = DEFAULT_SITE_ID,
            destination = DestinationDto(DEFAULT_REGION_ID),
            checkInDate = CheckDateDto(
                day = checkIn.first,
                month = checkIn.second,
                year = checkIn.third
            ),
            checkOutDate = CheckDateDto(
                day = checkOut.first,
                month = checkOut.second,
                year = checkOut.third
            ),
            rooms = listOf(RoomDto(adult, childList)),
            resultsStartingIndex = DEFAULT_RESULT_STARTING_INDEX,
            resultsSize = DEFAULT_RESULT_SIZE,
            sort = DEFAULT_SORT,
            filters = FiltersDto(
                PriceDto(
                    DEFAULT_MAX_PRICE, DEFAULT_MIN_PRICE
                )
            )

        )

        val result = api.getPropertiesList(request).data.propertySearch.properties
        PropertyMapper.map(result)
    }


    private fun getDate(date: String): Triple<Int, Int, Int> {
        val checkedInDateArray = date.split("/")
        // TODO Add check size==3
        val checkDay: Int = checkedInDateArray[0].toInt()
        val checkMonth: Int = checkedInDateArray[1].toInt()
        val checkYear: Int = checkedInDateArray[2].toInt()

        return Triple(checkDay, checkMonth, checkYear)
    }
}
