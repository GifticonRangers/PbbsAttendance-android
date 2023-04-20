package com.example.data.repository.remote.datasources

import com.example.data.api.UserService
import com.example.data.dto.UserResponseDto
import com.example.domain.model.dto.IdDto
import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun getUser(dto: IdDto): UserResponseDto
    suspend fun login(): UserResponseDto
}

class UserRemoteDataSourceImpl @Inject constructor(private val api: UserService) : UserRemoteDataSource{
    override suspend fun getUser(dto: IdDto): UserResponseDto {
        return api.getUser(dto)
    }

    override suspend fun login(): UserResponseDto {
        TODO("Not yet implemented")
    }

}