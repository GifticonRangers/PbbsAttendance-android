package com.example.data.repository.remote.datasources

import com.example.data.api.UserService
import com.example.data.dto.UserResponseDto
import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponseDto
    suspend fun login(): UserResponseDto
}

class UserRemoteDataSourceImpl @Inject constructor(private val api: UserService) : UserRemoteDataSource{
    override suspend fun getUser(): UserResponseDto {
        return api.getUser()
    }

    override suspend fun login(): UserResponseDto {
        TODO("Not yet implemented")
    }

}