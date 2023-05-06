package com.example.domain.usecases

import com.example.domain.model.UserBriefModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.AttendanceRepository
import javax.inject.Inject

class GetHoldAttendanceUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository){
    suspend fun invoke(dto:LectureInfoDto):ArrayList<UserBriefModel>{
        return attendanceRepository.showHoldAttendance(dto)
    }
}