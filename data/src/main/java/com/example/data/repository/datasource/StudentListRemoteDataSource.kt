package com.example.data.repository.datasource

import com.example.data.api.StudentListService
import com.example.data.dto.StudentListResponse
import javax.inject.Inject

interface StudentListRemoteDataSource {
    suspend fun getStudentList(): StudentListResponse
}

class StudentListRemoteDataSourceImpl @Inject constructor(private val api: StudentListService): StudentListRemoteDataSource{
    override suspend fun getStudentList(): StudentListResponse {
        return api.getStudentList()
    }
}