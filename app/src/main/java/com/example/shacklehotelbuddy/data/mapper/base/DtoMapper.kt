package com.example.shacklehotelbuddy.data.mapper.base

interface DtoMapper<DTO, VO> {

    fun mapToDto(vo: VO): DTO

    fun mapToDto(list: Collection<VO>): List<DTO> {
        val result = ArrayList<DTO>()
        list.mapTo(result) { mapToDto(it) }
        return result
    }
}