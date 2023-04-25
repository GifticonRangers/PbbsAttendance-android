package com.example.domain.usecases

import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAttendanceTotalInfoUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    suspend fun invoke(dto:LectureInfoDto):AttendanceTotalModel{
        return attendanceRepository.showAttendanceTotalInfo(dto)
    }
}