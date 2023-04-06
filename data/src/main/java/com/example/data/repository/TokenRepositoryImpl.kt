package com.example.data.repository

import com.example.data.repository.datasource.TokenLocalDataSourceImpl
import com.example.data.repository.datasource.TokenRemoteSourceImpl
import com.example.domain.model.Token
import com.example.domain.repository.TokenRepositiory
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenRemoteSource: TokenRemoteSourceImpl,
    private val tokenLocalSource: TokenLocalDataSourceImpl
):TokenRepositiory{
    private var cachedToken: Token? = null

    override suspend fun getToken(): Token {
        val cachedToken = this.cachedToken
        if (cachedToken != null) {
            if (cachedToken.isNotExpired()) return cachedToken
            return get
        }
    }
}