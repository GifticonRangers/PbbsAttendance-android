package com.example.domain.usecases

import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject

class GetAttendanceStatusUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository){
    suspend fun invoke(dto:LectureInfoDto):ArrayList<AttendanceHistoryModel>{
        val result = attendanceRepository.showAttendanceByTime(dto)
        return result
    }
}