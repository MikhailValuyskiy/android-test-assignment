package com.example.shacklehotelbuddy.data.mapper.base

interface BaseMapper<VO, DTO> : VoMapper<VO, DTO>, DtoMapper<DTO, VO>