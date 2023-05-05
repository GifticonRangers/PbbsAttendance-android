package com.example.data.repository

import com.example.data.mapper.UserMapper
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.data.util.INF
import com.example.domain.model.AttendantModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.domain.repository.UserRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
):UserRepository {
    override suspend fun getUser(): UserModel {
        var result = UserModel(INF,"",TypeUser.NULL,"","","","",GenderUser.NULL)
        remoteDataSource.getUser().suspendOnSuccess {
            result = UserMapper.mapperToUser(this.data)
        }
        return result
    }

    override suspend fun getUserBySubjectId(dto: IdDto): ArrayList<UserModel> {
        val result = arrayListOf<UserModel>()

        remoteDataSource.getUserBySubjectId(dto).suspendOnSuccess {
            this.data.forEach {
                val value = UserMapper.mapperToUser(it)
                result.add(value)
            }
        }

        return result
    }

    override suspend fun showUserAttendanceBySubjectId(dto: LectureInfoDto): Flow<ArrayList<AttendantModel>> {
        return remoteDataSource.showUserAttendanceBySubjectId(dto)
    }

    override suspend fun getAttendantBySubjectId(dto: IdDto): ArrayList<AttendantModel> {
        val result = arrayListOf<AttendantModel>()

        remoteDataSource.getUserBySubjectId(dto).suspendOnSuccess {
            this.data.forEach {
                val value = UserMapper.mapToAttendantModelFromUserResponseDto(it)
                result.add(value)
            }
        }

        return result
    }
}