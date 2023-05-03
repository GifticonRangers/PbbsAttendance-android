package com.example.domain.usecases

import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject

class GetAttendanceHistoryUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository){
    suspend fun invoke(dto:StudentSubjectDto):ArrayList<AttendanceHistoryModel>{
        val result = attendanceRepository.showAttendanceHistory(dto)
        return result
    }
}