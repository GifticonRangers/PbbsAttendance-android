package com.example.data.api

import com.example.data.dto.UserResponse
import retrofit2.http.GET

interface UserService {
    @GET("/api/admin/show")
    suspend fun getUser(): UserResponse
}