package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserRemoteDataSource
):UserRepository {

    override suspend fun getUser(dto:IdDto): UserModel {
        return UserMapper.mapperToUser(dataSource.getUser(dto))
    }
}