package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.MainViewModel


@Composable
fun BeforeStartAttendanceManageScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scheduleSubject = viewModel.getScheduleSubject()
    
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        Button(
            onClick = { navController.navigate(route = Screen.AfterStartAttendanceManage.route) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Blue4),
            border = BorderStroke(1.dp, Blue3),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 68.dp, vertical = 36.dp)
                .height(55.dp)

        ) {
            Text(
                text = "출석 시작",
                style = TextStyle(
                    fontFamily = suit_regular,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp
                ),
                color = Grey
            )
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
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 10.dp)
        ){
            Text(text = "학생", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey)
            Text(text = "24", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start = 5.dp))
        }
        LazyColumn {
//            studentListStateContent?.let {
//                itemsIndexed(
//                    it.content
//                ) { index, item ->
//                    StudentCard(data = item)
//                    Log.i("Attendance", item.name)
//                }
//            }
        }
    }
}

//@Preview
//@Composable
//private fun BeforeStartAttendanceManagePreview(){
//    BeforeStartAttendanceManageScreen(navController = rememberNavController(),  )
//}