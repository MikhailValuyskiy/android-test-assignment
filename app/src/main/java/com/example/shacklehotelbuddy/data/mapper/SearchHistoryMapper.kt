package com.example.shacklehotelbuddy.data.mapper

import com.example.shacklehotelbuddy.data.database.SearchHistory
import com.example.shacklehotelbuddy.data.mapper.base.DboMapper
import com.example.shacklehotelbuddy.domain.model.SearchModel

object SearchHistoryMapper : DboMapper<SearchHistory, SearchModel> {

    override fun mapToDbo(vo: SearchModel): SearchHistory {
        return SearchHistory(
            id = vo.id,
            checkedInDate = vo.checkInDate,
            checkedOutDate = vo.checkOutDate,
            adults = vo.adultsNumber,
            children = vo.childrenNumber,
            date = vo.time
        )
    }

    override fun mapFromDbo(dbo: SearchHistory): SearchModel {
        return SearchModel(
            id = dbo.id,
            checkInDate = dbo.checkedInDate,
            checkOutDate = dbo.checkedOutDate,
            adultsNumber = dbo.adults,
            childrenNumber = dbo.children,
            time = dbo.date
        )
    }
}