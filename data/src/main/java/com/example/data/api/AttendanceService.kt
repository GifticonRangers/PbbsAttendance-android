package com.example.data.api

import com.example.data.dto.AttendanceDateResponseDto
import com.example.data.dto.AttendanceHistoryResponseDto
import com.example.data.dto.AttendanceTotalResponseDto
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.UserSubjectDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AttendanceService {
    @POST("/api/attendance/showAttendanceTimeList")
    suspend fun showAttendanceTimeList(@Body dto:UserSubjectDto):ApiResponse<ArrayList<AttendanceDateResponseDto>>

    @POST("/api/attendance/showAttendanceInfo")
    suspend fun showAttendanceInfo(@Body dto:LectureInfoDto):ApiResponse<AttendanceTotalResponseDto>

    @POST("/api/attendance/showAttendanceByUser")
    suspend fun showAttendanceByUser(@Body dto:UserSubjectDto):ApiResponse<ArrayList<AttendanceHistoryResponseDto>>
}