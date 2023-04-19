package com.example.data.repository.datasource

import com.example.data.dto.TokenResponse
import com.example.data.util.PreferenceUtil
import javax.inject.Inject

interface TokenLocalDataSource {
    fun getAccessToken():String
    suspend fun getRefreshToken():String
    suspend fun saveToken(token:TokenResponse)
}

class TokenLocalDataSourceImpl @Inject constructor(private val sharedPreferences: PreferenceUtil):TokenLocalDataSource{
    override fun getAccessToken(): String {
        return sharedPreferences.getValue("accessToken")
    }

    override suspend fun getRefreshToken(): String {
        return sharedPreferences.getValue("refreshToken")
    }

    override suspend fun saveToken(token: TokenResponse) {
        sharedPreferences.setValue("grantType",token.grantType)
        sharedPreferences.setValue("accessToken",token.accessToken)
        sharedPreferences.setValue("refreshToken",token.refreshToken)
    }
}