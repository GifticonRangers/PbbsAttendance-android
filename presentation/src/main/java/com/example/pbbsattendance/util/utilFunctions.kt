package com.example.pbbsattendance.util

fun mapScheduleDay(day:String):Int{
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