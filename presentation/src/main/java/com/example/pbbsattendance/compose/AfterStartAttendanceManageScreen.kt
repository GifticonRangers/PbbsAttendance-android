package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.LiveStatusView
import com.example.pbbsattendance.compose.component.StudentCountAndLectureTimeBar
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.MainViewModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AfterStartAttendanceManageScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scheduleSubject = viewModel.getScheduleSubject()
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)



    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            LectureTimeModalContent(state)
        },
    ) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LectureTitle(title = scheduleSubject.scheduleName)
            LiveStatusView(Blue3, Grey4)
            Button(
                onClick = { navController.navigate(route = Screen.BeforeStartAttendanceManage.route) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Grey4),
                border = BorderStroke(1.dp, Blue3),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 68.dp, vertical = 20.dp)
                    .height(55.dp)
            ) {
                Text(text = "출석 마감", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
            }
            Row(
                Modifier
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
////                        Student(name = "이영지", studentId = "202011111", attendanceState = "출석"),
////                        Student(name = "스누피", studentId = "201822222", attendanceState = "출석"),
////                        Student(name = "류승룡", studentId = "201133333", attendanceState = "미출석")
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
fun AfterStartAttendanceManagePreview() {
    AfterStartAttendanceManageScreen(navController = rememberNavController())
}