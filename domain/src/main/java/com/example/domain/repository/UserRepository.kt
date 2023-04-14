package com.example.domain.repository

import com.example.domain.model.UserModel

interface UserRepository {
    suspend fun login(): UserModel
    suspend fun getUser():UserModel
}