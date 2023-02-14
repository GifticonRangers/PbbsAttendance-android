package com.example.domain.model

import com.example.domain.utils.getDday
import java.util.Calendar

data class UserModel(
    val id: String,
    val pw: String,
    val name: String,
    val gender: String,
    val department: String,
    val registerYear: Int,
    val email: String,
    val thisYear: Int,
    val thisSemester: Int,
    val semesterDate: Long
)
