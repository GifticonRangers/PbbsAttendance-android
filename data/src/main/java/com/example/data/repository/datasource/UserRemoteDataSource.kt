package com.example.data.repository.remote.datasources

import com.example.data.api.UserService
import com.example.data.dto.UserResponse
import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponse
    suspend fun login(): UserResponse
}

class UserRemoteDataSourceImpl @Inject constructor(private val api: UserService) : UserRemoteDataSource{
    override suspend fun getUser(): UserResponse {
        return api.getUser()
    }

    override suspend fun login(): UserResponse {
        TODO("Not yet implemented")
    }

}