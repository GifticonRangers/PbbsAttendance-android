package com.example.data.api

import com.example.data.dto.AttendanceDateResponseDto
import com.example.domain.model.dto.UserSubjectDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AttendanceService {
    @POST("/api/attendance/showAttendanceInfo")
    suspend fun showAttendanceInfo(@Body dto:UserSubjectDto):ApiResponse<ArrayList<AttendanceDateResponseDto>>
}