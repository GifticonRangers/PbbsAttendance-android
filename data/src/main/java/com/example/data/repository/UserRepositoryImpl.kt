package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.type.GenderUser
import com.example.domain.repository.UserRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserRemoteDataSource
):UserRepository {
    override suspend fun getUser(): UserModel {
        var result = UserModel(null,"",null,"","","","",GenderUser.FEMALE)
        dataSource.getUser().suspendOnSuccess {
            result = UserMapper.mapperToUser(this.data)
        }
        return result
    }

    override suspend fun getUserBySubjectId(dto: IdDto): ArrayList<UserModel> {
        val result = arrayListOf<UserModel>()

        dataSource.getUserBySubjectId(dto).suspendOnSuccess {
            this.data.forEach {
                val value = UserMapper.mapperToUser(it)
                result.add(value)
            }
        }

        return result
    }
}