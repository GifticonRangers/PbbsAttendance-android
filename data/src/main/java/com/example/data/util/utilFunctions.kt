package com.example.data.util

fun getHour(text:String):Int{
    val hour = text.substring(0 until 3)
    return hour.toInt()
}

fun getMinute(text: String):Int{
    val minute = text.substring(4)
    return minute.toInt()
}