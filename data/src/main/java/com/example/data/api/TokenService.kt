package com.example.data.api

import com.example.data.dto.TokenResponse
import retrofit2.http.GET

interface TokenService {
    @GET("/api/admin/token")
    suspend fun getToken():TokenResponse
}