package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("typeUser") val typeUser: String,
    @SerializedName("idUser") val idUser: String,
    @SerializedName("pwUser") val pwUser: String,
    @SerializedName("nameUser") val nameUser: String,
    @SerializedName("phoneUser") val phoneUser: String,
    @SerializedName("emailUser") val emailUser: String,
    @SerializedName("dptUser") val departmentUser: String,
    @SerializedName("genderUser") val genderUser: String,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("authorities") val authorities: Array<AuthorityDto>,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("accountNonExpired") val accountNonExpired: Boolean,
    @SerializedName("accountNonLocked") val accountNonLocked: Boolean,
    @SerializedName("credentialsNonExpired") val credentialsNonExpired: Boolean,
)