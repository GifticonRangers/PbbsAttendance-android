package com.example.data.api

import com.example.data.dto.IdDto
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login/checkId")
    suspend fun checkId(@Body dto: IdDto): Boolean
}