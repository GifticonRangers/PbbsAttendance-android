package com.example.pbbsattendance.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.type.AttendanceState
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.mapAttendanceStateText
import com.example.pbbsattendance.viewmodel.AttendanceHistoryViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity

@Composable
fun AttendanceHistoryScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    attendanceHistoryViewModel: AttendanceHistoryViewModel = hiltViewModel()
){
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val user = mainViewModel.getUser()
    Log.i("AttendanceHistoryScreen","user.id: ${user.id}, scheduleSubject.originId: ${scheduleSubject.originId}")

    attendanceHistoryViewModel.getAttendanceHistory(StudentSubjectDto(user.id, scheduleSubject.originId))
    val historyList by attendanceHistoryViewModel.historyList.observeAsState(initial = emptyList())

    AttendanceHistoryScreen(scheduleSubject, historyList)
}

@Composable
fun AttendanceHistoryScreen(scheduleSubject: ScheduleEntity, historyList:List<AttendanceHistoryModel>) {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        LazyColumn{
            itemsIndexed(
                historyList
            ){ index, item ->
                HistoryCard(item)
            }
        }
    }
}

@Composable
fun HistoryCard(data: AttendanceHistoryModel){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = data.week, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = "주차 ", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = data.time, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = "차시 ", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = mapAttendanceStateText(data.stateAttendance.state), style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = "처리 되었어요!", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = data.date, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 8.sp), color = Black1)
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        color = Grey3,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 1f
                    )
                }
        ){}
    }
}

@Preview
@Composable
private fun AttendanceCheckPreview(){
    AttendanceHistoryScreen(
        scheduleSubject = ScheduleEntity(originId = 0, scheduleDay = 30, scheduleName = "캡스톤디자인(2)", roomInfo = "505호", startTime = "10:00", endTime = "11:50"),
        historyList = listOf(AttendanceHistoryModel("23/03/08", "1","1",AttendanceState.ATTENDANCE,5,1,855))
    )
}