package com.example.domain.model

import com.example.domain.model.type.AttendanceState

data class AttendanceHistoryModel(
    val date: String,
    val week: String,
    val time: String,
    val stateAttendance:AttendanceState,
    val idProfessor: Int,
    val idStudent: Int,
    val idSubject: Int
)
