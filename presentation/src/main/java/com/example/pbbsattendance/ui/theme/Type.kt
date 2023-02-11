package com.example.pbbsattendance.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.R

val suit_semibold = FontFamily(
    Font(R.font.suit_semibold),
)
val suit_medium = FontFamily(
    Font(R.font.suit_medium),
)
val suit_regular = FontFamily(
    Font(R.font.suit_regular),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
)