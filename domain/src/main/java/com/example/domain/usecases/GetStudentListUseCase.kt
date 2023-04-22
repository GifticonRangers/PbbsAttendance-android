package com.example.domain.usecases

import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStudentListUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend fun invoke(dto:IdDto):ArrayList<UserModel>{
        val result = userRepository.getUserBySubjectId(dto)
        return result
    }
}