package com.example.data.mapper

import com.example.data.dto.UserResponseDto
import com.example.data.util.INF
import com.example.domain.model.UserModel
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser

object UserMapper {
    fun mapperToUser(userResponse: UserResponseDto): UserModel {
        return UserModel(
            id = userResponse.id?:INF,
            idUser = userResponse.idUser?:"",
            typeUser = userResponse.typeUser?:TypeUser.NULL,
            nameUser = userResponse.nameUser?:"",
            phoneUser = userResponse.phoneUser?:"",
            emailUser = userResponse.emailUser?:"",
            departmentUser = userResponse.departmentUser?:"",
            genderUser = userResponse.genderUser?:GenderUser.NULL
        )
    }
}