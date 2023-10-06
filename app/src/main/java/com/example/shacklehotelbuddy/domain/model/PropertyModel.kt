package com.example.shacklehotelbuddy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertyModel(
    val id: String?,
    val name: String?,
    val propertyImage: String?,
    val priceString: String?,
    val locationName: String?,
    val rating: String?
) : Parcelable