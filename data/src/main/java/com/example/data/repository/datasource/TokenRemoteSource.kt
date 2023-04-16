package com.example.data.repository.datasource

import com.example.data.api.TokenService
import com.example.data.dto.TokenResponse
import javax.inject.Inject

interface TokenRemoteDataSource {
    suspend fun getToken():TokenResponse
}

class TokenRemoteSourceImpl @Inject constructor(private val api: TokenService):TokenRemoteDataSource{
    override suspend fun getToken(): TokenResponse {
        return api.getToken()
    }
}