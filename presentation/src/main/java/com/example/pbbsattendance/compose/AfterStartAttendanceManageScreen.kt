package com.example.pbbsattendance.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.AttendantModel
import com.example.domain.model.type.AttendanceState
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.RoundLiveCard
import com.example.pbbsattendance.compose.component.StudentWithSwitchCard
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.AfterStartAttendanceManageViewModel
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
    val startNfcResponseCode by remember {
        mutableStateOf(afterStartAttendanceManageViewModel.startNfcTagResponseCode)
    }
    LaunchedEffect(Unit) {
        afterStartAttendanceManageViewModel.startNfcTag(lectureTimeItem, idSubject = scheduleSubject.originId)
        afterStartAttendanceManageViewModel.collectLiveAttendanceTotalInfo(lectureTimeItem, idSubject = scheduleSubject.originId)
        afterStartAttendanceManageViewModel.collectLiveAttendanceList(lectureTimeItem, scheduleSubject.originId)
        Log.i("AfterStartAttendanceManageScreen","LaunchedEffect")
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(afterStartAttendanceManageViewModel.attendanceTotal, lifecycleOwner) {
        afterStartAttendanceManageViewModel.attendanceTotal.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val flowLifecycleAware2 = remember(afterStartAttendanceManageViewModel.attendanceList, lifecycleOwner) {
        afterStartAttendanceManageViewModel.attendanceList.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val attendanceTotal by flowLifecycleAware.collectAsState(initial = AttendanceTotalModel(0,0,0,0))
    //val attendanceList by flowLifecycleAware2.collectAsState(initial = emptyList())
    val initialAttendantList by afterStartAttendanceManageViewModel.initialAttendanceList.observeAsState(initial = emptyArray())

    if (startNfcResponseCode.value == "200"){
        AfterStartAttendanceManageScreen(
            attendanceTotalModel = attendanceTotal,
            scheduleSubject = scheduleSubject,
            onFinishAttendance = {
                afterStartAttendanceManageViewModel.endNfcTag(lectureTimeItem, idSubject = scheduleSubject.originId)
                afterStartAttendanceManageViewModel.stopLiveAttendanceTotalInfo()
                navController.navigate(route = Screen.BeforeStartAttendanceManage.route)
            },
            currentLectureTime = lectureTimeItem,
            attendanceList = initialAttendantList
        )
    }
    else{
        EmptyScreen()
    }
}

@Composable
fun EmptyScreen(){
    Column() {
        Text("nfc출석을 시작하는데 문제가 생겼습니다. 빠른 시일 내에 복구할 예정입니다.")
    }
}

@Composable
fun AfterStartAttendanceManageScreen(attendanceTotalModel: AttendanceTotalModel, scheduleSubject:ScheduleEntity, onFinishAttendance:()->Unit = {}, currentLectureTime:LectureTimeItemModel, attendanceList:Array<AttendantModel>){
    val sizeOfDataToShow = 4//attendanceTotalModel.let { it.attendance!! + it.absence!! + it.late!! + it.public_ABSENCE!! }
    Log.i("AfterStartAttendanceManageScreen.sizeOfDataToShow","${sizeOfDataToShow}")
    val sizeOfData = attendanceList.size
    Log.i("AfterStartAttendanceManageScreen.sizeOfData","${sizeOfData}")
    val latestData = attendanceList.sliceArray((sizeOfData - sizeOfDataToShow)..(sizeOfData-1))
    latestData.forEach {
        Log.i("AfterStartAttendanceManageScreen.latestData","${it.name}")
    }

    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        Row(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            RoundLiveCard(number = attendanceTotalModel.attendance!!, text = "출석", color = Blue3)
            RoundLiveCard(number = attendanceTotalModel.absence!!, text = "결석", color = Grey4)
        }
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
                Text(text = latestData.size.toString(), style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start = 5.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = currentLectureTime.week + "주차" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                Text(text = currentLectureTime.time + "차시" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                Text(text = currentLectureTime.date ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
            }
        }
            LazyColumn {
                itemsIndexed(
                    latestData
                ) { index, item ->
                    StudentWithSwitchCard(data = item)
                }
            }
    }
}

@Preview
@Composable
fun AfterStartAttendanceManagePreview() {
    AfterStartAttendanceManageScreen(
        attendanceTotalModel=AttendanceTotalModel(0,0,0,0),
        scheduleSubject = ScheduleEntity(originId = 0, scheduleDay = 30, scheduleName = "캡스톤디자인(2)", roomInfo = "505호", startTime = "10:00", endTime = "11:50"),
        onFinishAttendance={},
        currentLectureTime = LectureTimeItemModel(time="1", date = "23/04/30",week="9"),
        attendanceList = arrayOf(AttendantModel(0,"2020201541","이용인", AttendanceState.ATTENDANCE))
    )
}