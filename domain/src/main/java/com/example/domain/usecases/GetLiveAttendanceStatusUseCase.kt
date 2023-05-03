package com.example.domain.usecases

import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLiveAttendanceStatusUseCase @Inject constructor(private val attendanceRepository: AttendanceRepository){
    suspend fun invoke(dto:StudentSubjectDto): Flow<ArrayList<AttendanceHistoryModel>> {
        val result = attendanceRepository.showLiveAttendanceHistory(dto)
        return result
    }
}