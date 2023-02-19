package com.example.data.datasources

import com.example.data.api.UserApi
import com.example.data.entity.UserResponse

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponse
    suspend fun login(): UserResponse
}

class UserRemoteDataSourceImpl(private val api: UserApi): UserRemoteDataSource{
    override suspend fun getUser(): UserResponse {
        return api.getUser()
    }

    override suspend fun login(): UserResponse {
        TODO("Not yet implemented")
    }

}