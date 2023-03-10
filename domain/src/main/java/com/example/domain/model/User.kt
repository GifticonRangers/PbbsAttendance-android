package com.example.domain.model

data class User(
    val id: Long,
    val userId: String,
    val pw: String,
    val name: String,
    val type: String,
    val gender: String,
    val department: String,
    val registerYear: String,
    val email: String,
    val thisYear: String,
    val thisSemester: String,
    val dayOfSemester: String
)
