package com.example.domain.usecases

import com.example.domain.model.AttendantModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLiveAttendantListUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend fun invoke(dto:LectureInfoDto): Flow<List<AttendantModel>> {
        val result = userRepository.showUserAttendanceBySubjectId(dto)
        return result
    }
}