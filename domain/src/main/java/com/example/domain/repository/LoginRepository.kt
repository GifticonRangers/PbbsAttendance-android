package com.example.domain.repository

interface LoginRepository {
    suspend fun checkId(id:String):Boolean
}