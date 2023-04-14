package com.example.pbbsattendance.util

fun checkIdRegex(text:String){}

fun changeToPhoneNumber(text:String):String{
    val subString1 = text.substring(0 until 3)
    val subString2 = text.substring(3 until 7)
    val subString3 = text.substring(7)

    return String.format("%s-%s-%s", subString1,subString2,subString3)
}
