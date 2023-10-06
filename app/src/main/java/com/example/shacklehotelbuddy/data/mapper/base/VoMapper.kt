package com.example.shacklehotelbuddy.data.mapper.base

interface VoMapper<VO, DTO> {

    fun map(dto: DTO): VO

    fun map(list: Collection<DTO>): List<VO> {
        val result = ArrayList<VO>()
        list.mapTo(result) { map(it) }
        return result
    }
}