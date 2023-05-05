package com.example.data.api

import com.example.data.dto.AttendanceHistoryResponseDto
import com.example.data.dto.AttendantResponseDto
import com.example.data.dto.UserResponseDto
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.LectureInfoDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("/api/user/showPersonalUser")
    suspend fun getUser(): ApiResponse<UserResponseDto>

    @POST("/api/user/showUserBySubjectId")
    suspend fun getUserBySubjectId(@Body dto:IdDto):ApiResponse<ArrayList<UserResponseDto>>

    @POST("/api/user/showUserAttendanceBySubjectId")
    suspend fun collectUserAttendanceBySubjectId(
        @Body dto: LectureInfoDto
    ):ApiResponse<ArrayList<AttendantResponseDto>>
}