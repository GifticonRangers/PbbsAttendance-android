package com.example.data.repository

import com.example.data.mapper.StudentListMapper
import com.example.data.repository.datasource.StudentListRemoteDataSource
import com.example.domain.model.StudentList
import com.example.domain.repository.StudentListRepository
import javax.inject.Inject

class StudentListRepositoryImpl @Inject constructor(private val dataSource: StudentListRemoteDataSource ):StudentListRepository {
    override suspend fun getStudentList(): StudentList {
        return StudentListMapper.mapperToStudent(dataSource.getStudentList())
    }
}