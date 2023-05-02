package com.example.pbbsattendance.compose

import androidx.compose.foundation.Canvas
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
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.type.AttendanceState
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.dummyData.AttendanceStatus
import com.example.pbbsattendance.dummyData.Lecture
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.colorMapper
import com.example.pbbsattendance.util.mapAttendanceBinaryStateText
import com.example.pbbsattendance.util.mapAttendanceStateText
import com.example.pbbsattendance.viewmodel.AttendanceStatusViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity

@Composable
fun AttendanceStatusScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    attendanceStatusViewModel: AttendanceStatusViewModel = hiltViewModel()
) {
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val lectureTime = mainViewModel.getLectureTimeItem()

    attendanceStatusViewModel.getAttendanceStatus(LectureInfoDto(lectureTime.week, lectureTime.time, scheduleSubject.originId.toString()))
    val statusList by attendanceStatusViewModel.statusList.observeAsState(initial = emptyList())

    AttendanceStatusScreen(scheduleSubject, statusList)
}

@Composable
fun AttendanceStatusScreen(scheduleSubject: ScheduleEntity,statusList:List<AttendanceHistoryModel>){
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        LazyColumn{
            itemsIndexed(
                statusList
            ){ index, item ->
                StatusCard(item)
            }
        }
    }
}

@Composable
fun StatusCard(data: AttendanceHistoryModel){
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
                Text(text = data.time, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = "차시", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = mapAttendanceBinaryStateText(data.stateAttendance.state), style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Blue2)
                Canvas(modifier = Modifier
                    .size(26.dp)
                    .padding(horizontal = 8.dp), onDraw = { drawCircle(color = colorMapper(data.stateAttendance.state))})
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
private fun AttendanceStatusPreview(){
    AttendanceStatusScreen(
        scheduleSubject = ScheduleEntity(originId = 0, scheduleDay = 30, scheduleName = "캡스톤디자인(2)", roomInfo = "505호", startTime = "10:00", endTime = "11:50"),
        statusList = listOf(
            AttendanceHistoryModel("23/03/08", "1","1", AttendanceState.ATTENDANCE,5,1,855),
            AttendanceHistoryModel("23/03/08", "1","2", AttendanceState.ABSENCE,5,1,855),
            AttendanceHistoryModel("23/03/08", "1","3", AttendanceState.ABSENCE,5,1,855),
        )
    )
}