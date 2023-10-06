package com.example.shacklehotelbuddy.data.dto

import com.google.gson.annotations.SerializedName

// TODO Remove this constants in real app, only for demo
const val DEFAULT_CURRENCY = "USD"
const val DEFAULT_LOCALE = "en_US"
const val DEFAULT_RESULT_SIZE = 100
const val DEFAULT_RESULT_STARTING_INDEX = 0
const val DEFAULT_CHILDREN_AGE = 5
const val DEFAULT_REGION_ID = "6054439"
const val DEFAULT_SORT = "PRICE_LOW_TO_HIGH"
const val DEFAULT_EAP_ID = 1
const val DEFAULT_SITE_ID = 300000001
const val DEFAULT_MAX_PRICE = 150
const val DEFAULT_MIN_PRICE = 100

class PropertiesDetailsRequest(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("propertyId")
    val propertyId: String,
    @SerializedName("siteId")
    val siteId: Int
)

