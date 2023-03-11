package com.example.pbbsattendance.util

import androidx.compose.ui.graphics.Color
import com.example.pbbsattendance.ui.theme.*

fun colorMapper(status: String): Color {
    when(status){
        "출석" -> return Blue5
        "지각" -> return Orange1
        "결석" -> return Pink
        "공결" -> return Green
        else -> return Black1
    }
}