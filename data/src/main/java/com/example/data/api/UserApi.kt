package com.example.data.api

import com.example.data.entity.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("/api/admin/show")
    suspend fun getUser(): UserResponse
}