package com.example.pbbsattendance.mapper

import com.example.domain.model.UserBriefModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdAttendanceStateDto
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.model.UserNewStateModel

object UserMapper {
    fun mapToUserFromUserBrief(dto:UserBriefModel):UserModel{
        return UserModel(
            id = dto.id,
            idUser = dto.idUser,
            nameUser = dto.name,
            typeUser = TypeUser.STUDENT,
            phoneUser = "empty_mapFromUserBrief",
            emailUser = "empty_mapFromUserBrief",
            departmentUser = "empty_mapFromUserBrief",
            genderUser = GenderUser.NULL
        )
    }

    fun mapToIdAttendanceFromUserNewState(dto:UserNewStateModel):IdAttendanceStateDto{
        return IdAttendanceStateDto(
            id = dto.userBriefModel.id,
            stateAttendance = dto.state
        )
    }
}