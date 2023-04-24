package com.example.data.api

import com.example.data.dto.UserResponseDto
import com.example.domain.model.dto.IdDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/user/showPersonalUser")
    suspend fun getUser(): ApiResponse<UserResponseDto>

    @POST("/api/user/showUserBySubjectId")
    suspend fun getUserBySubjectId(@Body dto:IdDto):ApiResponse<ArrayList<UserResponseDto>>
}