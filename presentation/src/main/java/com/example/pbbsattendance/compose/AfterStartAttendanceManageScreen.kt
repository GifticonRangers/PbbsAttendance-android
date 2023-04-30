package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.dto.IdDto
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.LiveStatusView
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.AfterStartAttendanceManageViewModel
import com.example.pbbsattendance.viewmodel.BeforeStartAttendanceManageViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity

@Composable
fun AfterStartAttendanceManageScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    afterStartAttendanceManageViewModel: AfterStartAttendanceManageViewModel = hiltViewModel(),
) {
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val lectureTimeItem = mainViewModel.getLectureTimeItem()

    afterStartAttendanceManageViewModel.getAttendanceTotalInfo(lectureTimeItem,scheduleSubject.originId)

    val attendanceTotal by afterStartAttendanceManageViewModel.attendanceTotal.observeAsState(AttendanceTotalModel(0,0,0,0))

    AfterStartAttendanceManageScreen(
        attendanceTotalModel = attendanceTotal,
        scheduleSubject = scheduleSubject,
        onFinishAttendance = {navController.navigate(route = Screen.BeforeStartAttendanceManage.route)},
        currentLectureTime = lectureTimeItem
    )

}

@Composable
fun AfterStartAttendanceManageScreen(attendanceTotalModel: AttendanceTotalModel, scheduleSubject:ScheduleEntity, onFinishAttendance:()->Unit = {}, currentLectureTime:LectureTimeItemModel){
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        LiveStatusView(Blue3, Grey4, attendanceTotalModel)
        Button(
            onClick = { onFinishAttendance() },
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
                        strokeWidth = 5f
                    )
                }
        ){}
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row() {
                Text(text = "학생", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start = 10.dp))
                Text(text = "24", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start = 5.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = currentLectureTime.week + "주차" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                Text(text = currentLectureTime.time + "차시" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                Text(text = currentLectureTime.date ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
            }
        }
//            LazyColumn {
//                itemsIndexed(
//                    dateList
//                ) { index, item ->
//                    Text(text = "모르게따")
//                }
//            }
    }
}
//@Preview
//@Composable
//fun AfterStartAttendanceManagePreview() {
//    AfterStartAttendanceManageScreen(navController = rememberNavController())
//}