package com.example.data.repository.remote.datasources

import com.example.data.api.UserService
import com.example.data.dto.UserResponseDto
import com.example.data.mapper.UserMapper
import com.example.domain.model.AttendantModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.LectureInfoDto
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun getUser(): ApiResponse<UserResponseDto>
    suspend fun getUserBySubjectId(dto:IdDto): ApiResponse<ArrayList<UserResponseDto>>
    suspend fun showUserAttendanceBySubjectId(dto: LectureInfoDto): Flow<ArrayList<AttendantModel>>
}

class UserRemoteDataSourceImpl @Inject constructor(private val api: UserService) : UserRemoteDataSource{
    override suspend fun getUser(): ApiResponse<UserResponseDto> {
        return api.getUser()
    }

    override suspend fun getUserBySubjectId(dto: IdDto): ApiResponse<ArrayList<UserResponseDto>> {
        return api.getUserBySubjectId(dto)
    }

    override suspend fun showUserAttendanceBySubjectId(dto: LectureInfoDto): Flow<ArrayList<AttendantModel>> {
        val refreshIntervalMs:Long = 3000
        var result = ArrayList<AttendantModel>()
        val attendanceByTime: Flow<ArrayList<AttendantModel>> = flow{
            while (true){
                api.collectUserAttendanceBySubjectId(dto).suspendOnSuccess {
                    this.data.forEach {
                        val value = UserMapper.mapToAttendantModelFromAttendantResponseDto(it)
                        result.add(value)
                    }
                }
                emit(result)
                delay(refreshIntervalMs)
            }
        }
        return attendanceByTime
    }
}