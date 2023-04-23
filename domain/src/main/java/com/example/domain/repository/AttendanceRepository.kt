package com.example.domain.repository

import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.UserSubjectDto

interface AttendanceRepository {
    suspend fun showAttendanceTimeList(dto:UserSubjectDto):ArrayList<LectureDateModel>
}