package com.example.data.repository.datasource

import com.example.data.api.TokenService
import com.example.data.dto.TokenResponse
import javax.inject.Inject

class TokenRemoteSourceImpl @Inject constructor(private val api: TokenService):OnlineTokenDataSource{
    override suspend fun getToken(): TokenResponse? {
        return api.getToken()
    }

    override suspend fun saveToken(token: TokenResponse) {
        TODO("Not yet implemented")
    }
}