package com.example.domain.model

import com.example.domain.model.type.AttendanceState

data class AttendantModel(
    var id:Int?,
    var idUser:String?,
    var name:String?,
    var state:AttendanceState?
)
