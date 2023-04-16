package com.example.data.repository

import com.example.data.repository.datasource.TokenLocalDataSourceImpl
import com.example.data.repository.datasource.TokenRemoteSourceImpl
import com.example.domain.repository.TokenRepositiory
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenRemoteSource: TokenRemoteSourceImpl,
    private val tokenLocalSource: TokenLocalDataSourceImpl
): TokenRepositiory {

    override suspend fun getToken():String {
        var cachedToken = tokenLocalSource.getAccessToken()
        if (cachedToken == ""){
            val newToken = tokenRemoteSource.getToken()
            tokenLocalSource.saveToken(newToken)
            cachedToken = tokenLocalSource.getAccessToken()
        }
        return cachedToken
    }
}