package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token") val token: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("expireDate") val expireDate: String
)
