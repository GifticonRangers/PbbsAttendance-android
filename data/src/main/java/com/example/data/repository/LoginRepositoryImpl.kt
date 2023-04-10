package com.example.data.repository

import android.util.Log
import com.example.data.api.LoginService
import com.example.data.dto.IdDto
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
):LoginRepository {
    override suspend fun checkId(id: String): Boolean {
        val result = api.checkId(IdDto(idUser = id))
        Log.i("LoginRepositoryImpl:",result.toString())
        return result
    }
}