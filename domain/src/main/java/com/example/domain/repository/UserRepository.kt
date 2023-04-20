package com.example.domain.repository

import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto

interface UserRepository {
    suspend fun getUser(dto:IdDto):UserModel
}