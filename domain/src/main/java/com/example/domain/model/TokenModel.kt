package com.example.domain.model

data class TokenModel(
    val grantType: String?,
    val accessToken: String?,
    val refreshToken: String?
)
