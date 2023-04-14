package com.example.data.repository

import com.example.data.api.LoginService
import com.example.domain.model.dto.IdDto
import com.example.data.mapper.UserMapper
import com.example.domain.model.UserModel
import com.example.domain.model.dto.UserDto
import com.example.domain.repository.LoginRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService,
):LoginRepository {

    override suspend fun checkId(id: String):Boolean{
        var result = false
        api.checkId(IdDto(idUser = id)).suspendOnSuccess {
            result = this.data
        }
        return result
    }

    override suspend fun signup(dto: UserDto): UserModel {
        var signupResult = UserModel(
            id = null,
            idUser = null,
            pwUser = null,
            name = null,
            phone = null,
            email = null,
            department = null,
            userType = null,
            genderType = null)
        api.signup(dto).suspendOnSuccess {
            signupResult = UserMapper.mapperToUser(this.data)
        }
        return signupResult
    }
}