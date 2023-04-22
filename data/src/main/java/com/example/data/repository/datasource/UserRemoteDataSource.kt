package com.example.data.repository.remote.datasources

import com.example.data.api.UserService
import com.example.data.dto.UserResponseDto
import com.example.domain.model.dto.IdDto
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun getUser(dto: IdDto): ApiResponse<UserResponseDto>
    suspend fun getUserBySubjectId(dto:IdDto): ApiResponse<ArrayList<UserResponseDto>>
}

class UserRemoteDataSourceImpl @Inject constructor(private val api: UserService) : UserRemoteDataSource{
    override suspend fun getUser(dto: IdDto): ApiResponse<UserResponseDto> {
        return api.getUser(dto)
    }

    override suspend fun getUserBySubjectId(dto: IdDto): ApiResponse<ArrayList<UserResponseDto>> {
        return api.getUserBySubjectId(dto)
    }
}