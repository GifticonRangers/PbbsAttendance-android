package com.example.data.repository

import android.util.Log
import com.example.data.api.LoginService
import com.example.data.dto.TokenResponse
import com.example.domain.model.dto.IdDto
import com.example.data.mapper.UserMapper
import com.example.data.repository.datasource.TokenLocalDataSourceImpl
import com.example.domain.model.TokenModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.LoginDto
import com.example.domain.model.dto.UserDto
import com.example.domain.repository.LoginRepository
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService,
    private val tokenLocalDataSource: TokenLocalDataSourceImpl
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

    override suspend fun login(dto: LoginDto):Boolean {
        var success = false
        api.login(dto).suspendOnSuccess {
            val loginResult = this.data
            tokenLocalDataSource.saveToken(loginResult)
            success = true
        }
        return success
    }
}