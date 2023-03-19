package com.example.data.api

import com.example.data.dto.StudentListResponse
import retrofit2.http.GET

interface StudentListService {
    @GET("/api/admin/studentList")
    suspend fun getStudentList():StudentListResponse
}