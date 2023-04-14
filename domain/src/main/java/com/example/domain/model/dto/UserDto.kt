package com.example.domain.model.dto


data class UserDto(
    val id: Int?,
    val idUser: String,
    val pwUser: String,
    val name: String,
    val phone: String,
    val email: String,
    val department: String,
    val type: Int,
    val gender: Int
)