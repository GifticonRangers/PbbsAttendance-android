package com.example.data.dto

import com.google.gson.annotations.SerializedName

class AttendanceTotalResponseDto (
    @SerializedName("attendance") val attendance:Int?,
    @SerializedName("late") val late:Int?,
    @SerializedName("absence") val absence:Int?,
    @SerializedName("public_ABSENCE") val public_ABSENCE:Int?,
)