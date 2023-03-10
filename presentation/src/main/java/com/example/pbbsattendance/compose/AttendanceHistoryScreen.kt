package com.example.pbbsattendance.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AttendanceHistoryScreen() {
    LectureTitle(title = "물류의 이해", id = "XAA8057001")
}

@Preview
@Composable
private fun AttendanceCheckPreview(){
    AttendanceHistoryScreen()
}