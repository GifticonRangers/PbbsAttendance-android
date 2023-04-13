package com.example.data.api

import com.example.data.dto.IdDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login/checkId")
    suspend fun checkId(@Body dto: IdDto): ApiResponse<Boolean>
}