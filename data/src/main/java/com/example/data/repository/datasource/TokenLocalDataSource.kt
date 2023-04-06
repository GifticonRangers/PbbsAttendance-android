package com.example.data.repository.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.data.dto.TokenResponse
import javax.inject.Inject

interface OnlineTokenDataSource {
    suspend fun getToken():TokenResponse?
    suspend fun saveToken(token:TokenResponse)
}

class TokenLocalDataSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences):OnlineTokenDataSource{
    override suspend fun getToken(): TokenResponse? {
        return TokenResponse(
            token = sharedPreferences.getString("token","")?: return null,
            refreshToken = sharedPreferences.getString("refreshToken", "")?: return null,
            expireDate = sharedPreferences.getString("expireDate", "")?: return null
        )
    }

    override suspend fun saveToken(token: TokenResponse) {
        sharedPreferences.edit {
            putString("token",  token.token)
            putString("refreshToken", token.refreshToken)
            putString("expireDate", token.expireDate)
        }
    }
}