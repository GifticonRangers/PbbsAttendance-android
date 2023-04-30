package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.dto.UserSubjectDto
import com.example.pbbsattendance.R
import com.example.pbbsattendance.compose.LectureTimeModalContent
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.Blue3
import com.example.pbbsattendance.ui.theme.Grey
import com.example.pbbsattendance.ui.theme.Grey4
import com.example.pbbsattendance.ui.theme.suit_regular
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.example.pbbsattendance.viewmodel.WithHoldListViewModel
import com.islandparadise14.mintable.ScheduleEntity
import kotlinx.coroutines.launch

@Composable
fun WithHoldListScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    withHoldListViewModel: WithHoldListViewModel = hiltViewModel()
){
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val lectureTimeItem = mainViewModel.getLectureTimeItem()
    val user = mainViewModel.getUser()

    withHoldListViewModel.getAttendanceTotalInfo(lectureTimeItem,scheduleSubject.originId)
    withHoldListViewModel.getAttendanceDateList(UserSubjectDto(idUser = user.id, idSubject = scheduleSubject.originId))
    val attendanceTotal by withHoldListViewModel.attendanceTotal.observeAsState(AttendanceTotalModel(0,0,0,0))
    val dateList by withHoldListViewModel.dateList.observeAsState(initial = emptyList())
    //리컴포지션이 될때 계속 초기화가 되는 게 문제
    WithHoldListScreen(
        attendanceTotal = attendanceTotal,
        dateList = dateList,
        scheduleSubject = scheduleSubject,
        onGetSelectedAttendance = { index ->
            withHoldListViewModel.getAttendanceTotalInfo(dateList[index],scheduleSubject.originId)
        },
        selectedLectureTime = lectureTimeItem
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun WithHoldListScreen(attendanceTotal:AttendanceTotalModel, dateList:List<LectureTimeItemModel>, scheduleSubject:ScheduleEntity, onGetSelectedAttendance:(Int)->Unit = {}, selectedLectureTime:LectureTimeItemModel){
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var lectureTime by remember{
        mutableStateOf(selectedLectureTime)
    }
    val onLectureTimeItemSelected = {index:Int ->
        onGetSelectedAttendance(index)
        lectureTime = dateList[index]
    }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            LectureTimeModalContent(state,dateList,{ index-> onLectureTimeItemSelected(index) })
        },
    ) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LectureTitle(title = scheduleSubject.scheduleName)
            LiveStatusView(Grey4, Blue3, attendanceTotal)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top=10.dp)
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
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(){
                    Text(text = "학생",style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    Text(text = "n",style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start=5.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = lectureTime.week + "주차" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    Text(text = lectureTime.time + "차시" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    Text(text = lectureTime.date ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    IconButton(onClick = { scope.launch { state.show() }}) {
                        Icon(
                            painterResource(id = R.drawable.ic_more),
                            contentDescription = null
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun WithHoldListScreenPreview() {
    WithHoldListScreen(
        AttendanceTotalModel(attendance = 0, late = 0, absence = 0, public_ABSENCE = 0),
        dateList = listOf(LectureTimeItemModel(time="1", date = "23/04/30",week="9")),
        scheduleSubject = ScheduleEntity(originId = 0, scheduleDay = 30, scheduleName = "캡스톤디자인(2)", roomInfo = "505호", startTime = "10:00", endTime = "11:50"),
        selectedLectureTime = LectureTimeItemModel(time="1", date = "23/04/30",week="9")
    )
}