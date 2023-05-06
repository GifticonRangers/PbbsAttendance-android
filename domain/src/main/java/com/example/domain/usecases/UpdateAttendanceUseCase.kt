package com.example.domain.usecases

import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.IdAttendanceStateDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject

class UpdateAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository){
    suspend fun invoke(dto:IdAttendanceStateDto):AttendanceHistoryModel{
        return attendanceRepository.updateAttendance(dto)
    }
}