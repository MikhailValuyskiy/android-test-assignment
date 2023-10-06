package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.dto.PropertyDto
import com.example.shacklehotelbuddy.data.mapper.base.VoMapper
import com.example.shacklehotelbuddy.domain.model.PropertyModel

object PropertyMapper : VoMapper<PropertyModel, PropertyDto> {

    override fun map(dto: PropertyDto): PropertyModel {
        return PropertyModel(
            id = dto.id,
            name = dto.name,
            propertyImage = dto.propertyImageDto.image.url,
            priceString = "${dto.price.lead.formatted} ${dto.price.priceMessages[0].value}",
            locationName = dto.neighborhood?.name,
            rating = dto.star
        )
    }
}

