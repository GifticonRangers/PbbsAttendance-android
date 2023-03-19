package com.example.data.dto

import com.example.domain.model.Student
import com.google.gson.annotations.SerializedName

data class StudentListResponse(
    @SerializedName("content") val content: Array<Student>
)