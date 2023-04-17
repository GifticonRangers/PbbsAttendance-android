package com.example.data.repository

import com.example.data.api.LoginService
import com.example.domain.model.dto.IdUserDto
import com.example.data.repository.datasource.TokenLocalDataSourceImpl
import com.example.domain.model.dto.LoginDto
import com.example.domain.model.dto.UserDto
import com.example.domain.repository.LoginRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService,
    private val tokenLocalDataSource: TokenLocalDataSourceImpl
):LoginRepository {

    override suspend fun checkId(id: String):Boolean{
        var result = false
        api.checkId(IdUserDto(idUser = id)).suspendOnSuccess {
            result = this.data
        }
        return result
    }

    override suspend fun signup(dto: UserDto): Boolean {
        var result = false
        api.signup(dto).suspendOnSuccess {
            result = true
        }
        return result
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