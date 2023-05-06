package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class UserBriefResponseDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("idUser") val idUser: String?,
    @SerializedName("name") val name: String?,
)
