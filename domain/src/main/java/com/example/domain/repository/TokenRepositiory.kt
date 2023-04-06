package com.example.domain.repository

import com.example.domain.model.Token

interface TokenRepositiory {
    suspend fun getToken():Token
}