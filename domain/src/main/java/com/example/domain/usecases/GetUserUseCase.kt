package com.example.domain.usecases

import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor( private val repository: UserRepository) {
    suspend fun invoke(dto:IdDto): UserModel {
        return repository.getUser(dto)
    }
}