package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.dto.AvailabilityDto
import com.example.shacklehotelbuddy.data.dto.DestinationInfoDto
import com.example.shacklehotelbuddy.data.dto.DistanceFromDestinationDto
import com.example.shacklehotelbuddy.data.dto.ImageDto
import com.example.shacklehotelbuddy.data.dto.LeadDto
import com.example.shacklehotelbuddy.data.dto.NeighborhoodDto
import com.example.shacklehotelbuddy.data.dto.PriceMessageDto
import com.example.shacklehotelbuddy.data.dto.PropertyDto
import com.example.shacklehotelbuddy.data.dto.PropertyImageDto
import com.example.shacklehotelbuddy.data.dto.PropertyPriceDto
import org.junit.Assert
import org.junit.Test

class PropertyMapperTest {

    @Test
    fun propertyMapper_isCorrect() {

        // TODO parse some real json file
        val dto = PropertyDto(
            id = "1",
            name = "Test Name",
            propertyImageDto = PropertyImageDto(
                ImageDto(
                    description = "test_desc",
                    url = "test url"
                )
            ),
            neighborhood = NeighborhoodDto("Diamond Bay Area"),
            star = "5",
            price = PropertyPriceDto(
                lead = LeadDto(144.915, "$145"), priceMessages = listOf(
                    PriceMessageDto("150")
                )
            ),
            destinationInfo = DestinationInfoDto(
                DistanceFromDestinationDto(
                    unit = "MILE", value = 12.22
                )
            ),
            availability = AvailabilityDto(available = true, minRoomsLeft = 1)
        )

        val model = PropertyMapper.map(dto)
        Assert.assertEquals(dto.id, model.id)
        Assert.assertEquals(dto.name, model.name)
        Assert.assertEquals(dto.propertyImageDto.image.url, model.propertyImage)
        Assert.assertEquals(
            "${dto.price.lead.formatted} ${dto.price.priceMessages[0].value}",
            model.priceString
        )
        Assert.assertEquals(dto.neighborhood?.name, model.locationName)
        Assert.assertEquals(dto.star, model.rating)
    }
}

