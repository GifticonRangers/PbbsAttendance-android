package com.example.data.mapper

import com.example.data.dto.AttendanceDateResponseDto
import com.example.data.util.INF
import com.example.domain.model.LectureDateModel

object AttendanceMapper {
    fun mapperToAttendanceDateMapper(dto:AttendanceDateResponseDto):LectureDateModel{
        return LectureDateModel(
            year = dto.year,
            month = dto.month,
            day = dto.day,
            week = dto.week,
            time = dto.time
        )
    }
}