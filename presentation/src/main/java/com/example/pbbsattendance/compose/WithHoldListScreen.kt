package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pbbsattendance.compose.LectureTimeModalContent
import com.example.pbbsattendance.ui.theme.Blue3
import com.example.pbbsattendance.ui.theme.Grey4

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun WithHoldListScreen(){
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
//            LectureTimeModalContent(state)
        },
    ) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LectureTitle(title = "물류의 이해")
            LiveStatusView(Grey4, Blue3)
            Row(
                Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Grey4,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 10f
                        )
                    }
            ){}
            StudentCountAndLectureTimeBar(course = 1, modalState = state)
            LazyColumn {
//                studentListStateContent?.let {
//                    itemsIndexed(
//                        it.content
//                    ) { index, item ->
//                        StudentCard(data = item)
//                    }
//                }
//                itemsIndexed(
//                    arrayOf(
//                        Student(name = "이영지", studentId = "202011111", attendanceState = "출석"),
//                        Student(name = "스누피", studentId = "201822222", attendanceState = "출석"),
//                        Student(name = "류승룡", studentId = "201133333", attendanceState = "미출석")
//                    )
//                ) { index, item ->
//                    StudentCard(data = item)
//                }
            }
        }
    }
}

@Preview
@Composable
fun WithHoldListScreenPreview() {
    WithHoldListScreen()
}