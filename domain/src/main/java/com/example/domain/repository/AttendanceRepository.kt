package com.example.domain.repository

import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.UserSubjectDto

interface AttendanceRepository {
    suspend fun showAttendanceTimeList(dto:UserSubjectDto):ArrayList<LectureDateModel>
    suspend fun showAttendanceTotalInfo(dto:LectureInfoDto):AttendanceTotalModel
}