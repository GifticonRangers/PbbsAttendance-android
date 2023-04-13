package com.example.data.repository

import android.util.Log
import com.example.data.api.LoginService
import com.example.data.dto.IdDto
import com.example.domain.repository.LoginRepository
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
):LoginRepository {
    private var result = true

    override suspend fun checkId(id: String):Boolean{
        api.checkId(IdDto(idUser = id)).suspendOnSuccess {
            result = this.data
        }
        return result
    }
}