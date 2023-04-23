package com.example.domain.usecases

import com.example.domain.model.LectureDateModel
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAttendanceDateList @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    suspend fun invoke(dto:UserSubjectDto):ArrayList<LectureDateModel>{
        val result = attendanceRepository.showAttendanceTimeList(dto)
        return result
    }
}