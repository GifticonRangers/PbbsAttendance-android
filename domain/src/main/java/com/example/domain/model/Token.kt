package com.example.domain.model

data class Token (
    val token:String,
    val refreshToken:String,
    val expireDate:String
)