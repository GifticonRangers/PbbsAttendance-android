package com.example.data.mapper

import com.example.data.dto.UserResponseDto
import com.example.domain.model.UserModel

object UserMapper {
    fun mapperToUser(userResponse: UserResponseDto): UserModel {
        return UserModel(
            id = userResponse.id,
            idUser = userResponse.idUser,
            pwUser = userResponse.pwUser,
            name = userResponse.nameUser,
            phone = userResponse.phoneUser,
            email = userResponse.emailUser,
            department = userResponse.departmentUser,
            userType =  userResponse.typeUser,
            genderType = userResponse.genderUser
        )
    }
}