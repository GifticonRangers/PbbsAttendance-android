package com.example.data.mapper

import com.example.data.dto.StudentListResponse
import com.example.domain.model.StudentList

object StudentListMapper {
    fun mapperToStudent(studentListResponse: StudentListResponse): StudentList{
        return StudentList(
            content = studentListResponse.content
        )
    }
}