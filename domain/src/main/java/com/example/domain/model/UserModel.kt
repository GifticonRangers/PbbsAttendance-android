package com.example.domain.model

import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser

data class UserModel(
    val id: Int,
    val idUser: String,
    val typeUser: TypeUser,
    val nameUser: String,
    val phoneUser: String,
    val emailUser: String,
    val departmentUser: String,
    val genderUser: GenderUser
)
