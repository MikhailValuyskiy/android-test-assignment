package com.example.shacklehotelbuddy.data.dto

import com.google.gson.annotations.SerializedName

data class PropertiesListResponse(
    @SerializedName("data")
    val data: PropertiesListResponseData
)

data class PropertiesListResponseData(
    @SerializedName("propertySearch")
    val propertySearch: PropertySearchEntity
)

data class PropertySearchEntity(
    @SerializedName("properties")
    val properties: List<PropertyDto>
)

data class PropertyDto(
    @SerializedName("availability")
    val availability: AvailabilityDto,
    @SerializedName("destinationInfo")
    val destinationInfo: DestinationInfoDto,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: PropertyPriceDto,
    @SerializedName("propertyImage")
    val propertyImageDto: PropertyImageDto,
    @SerializedName("neighborhood")
    val neighborhood: NeighborhoodDto?,
    @SerializedName("star")
    val star: String
)

data class NeighborhoodDto(
    @SerializedName("name")
    val name: String
)

data class AvailabilityDto(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("minRoomsLeft")
    val minRoomsLeft: Int
)

data class DestinationInfoDto(
    @SerializedName("distanceFromDestination")
    val distanceFromDestination: DistanceFromDestinationDto
)

data class PropertyPriceDto(
    @SerializedName("lead")
    val lead: LeadDto,
    @SerializedName("priceMessages")
    val priceMessages: List<PriceMessageDto>
)

data class PropertyImageDto(
    @SerializedName("image")
    val image: ImageDto
)

data class DistanceFromDestinationDto(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)

data class LeadDto(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("formatted")
    val formatted: String
)

data class PriceMessageDto(
    @SerializedName("value")
    val value: String
)

data class ImageDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)