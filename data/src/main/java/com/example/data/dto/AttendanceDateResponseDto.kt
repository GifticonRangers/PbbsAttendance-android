package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class AttendanceDateResponseDto(
    @SerializedName("year") val year:String?,
    @SerializedName("month") val month:String?,
    @SerializedName("day") val day:String?,
    @SerializedName("week") val week:String?,
    @SerializedName("time") val time:String?,
)
