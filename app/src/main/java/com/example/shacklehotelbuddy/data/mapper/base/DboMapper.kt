package com.example.shacklehotelbuddy.data.mapper.base

interface DboMapper<DBO, VO> {

    fun mapToDbo(vo: VO): DBO

    fun mapToDto(list: Collection<VO>): List<DBO> {
        val result = ArrayList<DBO>()
        list.mapTo(result) { mapToDbo(it) }
        return result
    }

    fun mapFromDbo(dbo: DBO): VO

    fun mapFromDbo(list: Collection<DBO>): List<VO> {
        val result = ArrayList<VO>()
        list.mapTo(result) { mapFromDbo(it) }
        return result
    }
}