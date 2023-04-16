package com.example.domain.repository

import com.example.domain.model.TokenModel

interface TokenRepositiory {
    suspend fun getToken(): String
}