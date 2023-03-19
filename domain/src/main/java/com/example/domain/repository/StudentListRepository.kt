package com.example.domain.repository

import com.example.domain.model.StudentList

interface StudentListRepository {
    suspend fun getStudentList():StudentList
}