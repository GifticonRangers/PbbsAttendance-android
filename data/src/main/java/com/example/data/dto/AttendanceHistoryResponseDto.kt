package com.example.data.dto

import com.example.domain.model.type.AttendanceState
import com.google.gson.annotations.SerializedName

data class AttendanceHistoryResponseDto(
    @SerializedName("id") val id:Int?,
    @SerializedName("dateAttendance") val dateAttendance:String?,
    @SerializedName("weekAttendance") val weekAttendance:String?,
    @SerializedName("timeAttendance") val timeAttendance:String?,
    @SerializedName("stateAttendance") val stateAttendance:AttendanceState?,
    @SerializedName("idProfessor") val idProfessor:Int?,
    @SerializedName("idStudent") val idStudent:Int?,
    @SerializedName("idSubject") val idSubject:Int?,
    @SerializedName("startAttendance") val startAttendance:String?,
    @SerializedName("endAttendance") val endAttendance:String?,
    @SerializedName("nfcCount") val nfcCount:Int?,
)