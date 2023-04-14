package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.domain.model.UserModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserRemoteDataSource
):UserRepository {
    override suspend fun login(): UserModel {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): UserModel {
        return UserMapper.mapperToUser(dataSource.getUser())
    }
}