package com.example.data.mapper

import com.example.data.dto.AttendanceDateResponseDto
import com.example.data.dto.AttendanceHistoryResponseDto
import com.example.data.dto.AttendanceTotalResponseDto
import com.example.data.util.INF
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.LectureDateModel
import com.example.domain.model.type.AttendanceState

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

    fun mapToAttendanceTotalMapper(dto:AttendanceTotalResponseDto):AttendanceTotalModel{
        return AttendanceTotalModel(
            attendance = dto.attendance,
            late = dto.late,
            absence = dto.absence,
            public_ABSENCE = dto.public_ABSENCE
        )
    }

    fun mapToAttendanceHistoryModelMapper(dto:AttendanceHistoryResponseDto):AttendanceHistoryModel{
        return AttendanceHistoryModel(
            date = dto.dateAttendance?:"",
            week = dto.weekAttendance?:"",
            time = dto.timeAttendance?:"",
            stateAttendance = dto.stateAttendance?:AttendanceState.NULL,
            idProfessor = dto.idProfessor!!,
            idStudent = dto.idSubject!!,
            idSubject = dto.idSubject!!,
        )
    }
}