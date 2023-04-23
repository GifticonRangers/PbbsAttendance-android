package com.example.data.util

fun getHour(text:String?):Int {
    var hour: String
    if(text != null) {
        hour = text.substring(0 until 2)
    }
    else hour = "0"

    return hour.toInt()
}

fun getMinute(text: String?):Int {
    var minute: String
    if(text != null) {
        minute = text.substring(3)
    }
    else minute = "00"

    return minute.toInt()
}

val INF = 1000000