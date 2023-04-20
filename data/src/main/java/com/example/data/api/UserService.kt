package com.example.data.api

import com.example.data.dto.UserResponseDto
import com.example.domain.model.dto.IdDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/user/showUserByIdUser")
    suspend fun getUser(@Body dto: IdDto): UserResponseDto
}