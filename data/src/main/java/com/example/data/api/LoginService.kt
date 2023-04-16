package com.example.data.api

import com.example.data.dto.TokenResponse
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.UserDto
import com.example.data.dto.UserResponseDto
import com.example.domain.model.TokenModel
import com.example.domain.model.dto.LoginDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login/checkId")
    suspend fun checkId(@Body dto: IdDto): ApiResponse<Boolean>

    @POST("/api/login/signup")
    suspend fun signup(@Body dto: UserDto): ApiResponse<UserResponseDto>

    @POST("/api/login/login")
    suspend fun login(@Body dto: LoginDto): ApiResponse<TokenResponse>
}