package com.example.data.mapper

import com.example.data.dto.UserResponseDto
import com.example.domain.model.UserModel

object UserMapper {
    fun mapperToUser(userResponse: UserResponseDto): UserModel {
        return UserModel(
            id = userResponse.id,
            idUser = userResponse.idUser,
            typeUser = userResponse.typeUser,
            nameUser = userResponse.nameUser,
            phoneUser = userResponse.phoneUser,
            emailUser = userResponse.emailUser,
            departmentUser = userResponse.departmentUser,
            genderUser = userResponse.genderUser
        )
    }
}