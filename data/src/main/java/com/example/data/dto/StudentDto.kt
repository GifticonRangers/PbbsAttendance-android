package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class StudentDto(
    @SerializedName("name") val name: String,
    @SerializedName("studentId") val studentId: String,
    @SerializedName("attendanceState") val attendanceState: String
)
