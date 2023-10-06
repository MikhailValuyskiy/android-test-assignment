package com.example.shacklehotelbuddy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchModel(
    val id: Int? = null,
    val checkInDate: String?,
    val checkOutDate: String?,
    val adultsNumber: Int?,
    val childrenNumber: Int?,
    val time: Long
): Parcelable