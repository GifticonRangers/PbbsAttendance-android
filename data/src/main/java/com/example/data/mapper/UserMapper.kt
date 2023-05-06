package com.example.data.mapper

import com.example.data.dto.AttendantResponseDto
import com.example.data.dto.UserBriefResponseDto
import com.example.data.dto.UserResponseDto
import com.example.data.util.INF
import com.example.domain.model.AttendantModel
import com.example.domain.model.UserBriefModel
import com.example.domain.model.UserModel
import com.example.domain.model.type.AttendanceState
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser

object UserMapper {
    fun mapperToUser(userResponse: UserResponseDto): UserModel {
        return UserModel(
            id = userResponse.id?: INF,
            idUser = userResponse.idUser?:"",
            typeUser = userResponse.typeUser?:TypeUser.NULL,
            nameUser = userResponse.nameUser?:"",
            phoneUser = userResponse.phoneUser?:"",
            emailUser = userResponse.emailUser?:"",
            departmentUser = userResponse.departmentUser?:"",
            genderUser = userResponse.genderUser?:GenderUser.NULL
        )
    }

    fun mapToAttendantModelFromUserResponseDto(dto:UserResponseDto):AttendantModel{
        return AttendantModel(
            id = dto.id,
            idUser = dto.idUser,
            name = dto.nameUser,
            state = AttendanceState.NULL
        )
    }

    fun mapToAttendantModelFromAttendantResponseDto(dto:AttendantResponseDto):AttendantModel{
        return AttendantModel(
            id = dto.id,
            idUser = dto.idUser,
            name = dto.name,
            state = dto.state
        )
    }

    fun mapToUserBriefModel(dto: UserBriefResponseDto): UserBriefModel {
        return UserBriefModel(
            id = dto.id?:INF,
            idUser = dto.idUser?:"",
            name = dto.name?:""
        )
    }
}