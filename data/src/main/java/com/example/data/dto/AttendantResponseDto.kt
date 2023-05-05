package com.example.data.dto

import com.example.domain.model.type.AttendanceState
import com.google.gson.annotations.SerializedName

data class AttendantResponseDto(
    @SerializedName("id") val id:Int?,
    @SerializedName("idUser") val idUser:String?,
    @SerializedName("name") val name:String?,
    @SerializedName("state") val state:AttendanceState,
)
