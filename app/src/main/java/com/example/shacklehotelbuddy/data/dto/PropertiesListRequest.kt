package com.example.shacklehotelbuddy.data.dto

import com.google.gson.annotations.SerializedName


data class PropertiesListRequest(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("siteId")
    val siteId: Int,
    @SerializedName("destination")
    val destination: DestinationDto,
    @SerializedName("checkInDate")
    val checkInDate: CheckDateDto,
    @SerializedName("checkOutDate")
    val checkOutDate: CheckDateDto,
    @SerializedName("rooms")
    val rooms: List<RoomDto>,
    @SerializedName("resultsStartingIndex")
    val resultsStartingIndex: Int,
    @SerializedName("resultsSize")
    val resultsSize: Int,
    @SerializedName("sort")
    val sort: String,
    @SerializedName("filters")
    val filters: FiltersDto
)

data class CheckDateDto(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
)

data class DestinationDto(
    @SerializedName("regionId")
    val regionId: String
)

data class FiltersDto(
    @SerializedName("price")
    val price: PriceDto
)

data class RoomDto(
    @SerializedName("adults")
    val adults: Int,
    @SerializedName("children")
    val children: List<ChildrenDto>
)

data class PriceDto(
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int
)

data class ChildrenDto(
    @SerializedName("age")
    val age: Int
)

