package com.example.domain.repository

import com.example.domain.model.Id

interface LoginRepository {
    suspend fun checkId(id:String):Boolean
}