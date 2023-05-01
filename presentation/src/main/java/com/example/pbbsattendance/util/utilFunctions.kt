package com.example.pbbsattendance.util

import com.example.domain.model.type.AttendanceState
import com.example.domain.model.type.TypeUser

fun mapScheduleDay(day:String?):Int{
    var result=0
    when(day){
        "월" -> result=0
        "화" -> result=1
        "수" -> result=2
        "목" -> result=3
        "금" -> result=4
        "토" -> result=5
        "일" -> result=6
    }
    return result
}

fun mapUserType(type:String?):String{
    var result = ""
    when(type){
        TypeUser.ADMIN.koName -> result="관리자"
        TypeUser.PROFESSOR.koName -> result="교수님"
        TypeUser.STUDENT.koName -> result="학생님"
    }
    return result
}

fun mapPepTalk(type:String?):String{
    var result = ""
    when(type){
        TypeUser.ADMIN.koName -> result="관리자님 안녕하세요!"
        TypeUser.PROFESSOR.koName -> result="오늘도 알찬 수업 부탁드려요!"
        TypeUser.STUDENT.koName -> result="오늘도 알찬 하루 되세요!"
    }
    return result
}

fun mapAttendanceStateText(text:String):String{
    var result = ""
    when(text){
        AttendanceState.ATTENDANCE.state -> result = "출석"
        AttendanceState.LATE.state -> result = "지각"
        AttendanceState.ABSENCE.state -> result = "결석"
        AttendanceState.PUBLIC_ABSENCE.state -> result = "공결"
    }
    return result
}