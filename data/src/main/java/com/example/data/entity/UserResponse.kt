package com.example.data.entity

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("typeUser") val typeUser: String,
    @SerializedName("idUser") val idUser: String,
    @SerializedName("pwUser") val pwUser: String,
    @SerializedName("nameUser") val nameUser: String,
    @SerializedName("phoneUser") val phoneUser: String,
    @SerializedName("emailUser") val emailUser: String,
    @SerializedName("dptUser") val departmentUser: String,
)
