package com.example.domain.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDayOfSemester(): String {
    val dateFormat = SimpleDateFormat("yyyyMMdd")
    val startDate = dateFormat.parse("20230217").time

    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time
    val dateOfSemester = (today - startDate) / (24 * 60 * 60 * 1000)
    return dateOfSemester.toString()

}

fun getSemester(): String {
    val today_month = Calendar.MONTH
    if (today_month >= 3 && today_month <= 6) return "1"
    if (today_month >= 9 && today_month <= 12) return "2"
    else return "계절"
}

fun getRegisterYear(id:String): String {
    var result = id.subSequence(2 until 4).toString()
    return result
}
fun main() {
    println(getDayOfSemester())
    println(getSemester())
    println(getRegisterYear("202001541"))
}
