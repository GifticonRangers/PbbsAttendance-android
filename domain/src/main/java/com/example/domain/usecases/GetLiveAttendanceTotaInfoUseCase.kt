package com.example.domain.usecases

import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLiveAttendanceTotalInfoUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository) {
    suspend fun invoke(dto:LectureInfoDto): Flow<AttendanceTotalModel> {
        return attendanceRepository.showLiveAttendanceTotalInfo(dto)
    }
}