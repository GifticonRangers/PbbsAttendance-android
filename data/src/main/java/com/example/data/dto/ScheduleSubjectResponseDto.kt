package com.example.data.dto

import com.google.gson.annotations.SerializedName

data class ScheduleSubjectResponseDto(
    @SerializedName("id") val id: Int,
    @SerializedName("startTimeSubject") val startTimeSubject: String,
    @SerializedName("endTimeSubject") val endTimeSubject: String,
    @SerializedName("daySubject") val daySubject: String,
    @SerializedName("nameSubject") val nameSubject: String,
    @SerializedName("locationSubject") val locationSubject: String,
    @SerializedName("profSubject") val profSubject: String,
)
