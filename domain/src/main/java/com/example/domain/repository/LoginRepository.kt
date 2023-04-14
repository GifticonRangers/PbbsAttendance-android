package com.example.domain.repository

import com.example.domain.model.UserModel
import com.example.domain.model.dto.UserDto

interface LoginRepository {
    suspend fun checkId(id:String):Boolean
    suspend fun signup(dto:UserDto): UserModel
}