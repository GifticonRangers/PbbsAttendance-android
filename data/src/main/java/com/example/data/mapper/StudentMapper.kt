package com.example.data.mapper

import com.example.data.dto.StudentDto
import com.example.domain.model.Student

class StudentMapper{
    fun mapperToStudent(studentDto: StudentDto): Student {
        return Student(
            name = studentDto.name,
            studentId = studentDto.studentId,
            attendanceState = studentDto.attendanceState
        )
    }
}
