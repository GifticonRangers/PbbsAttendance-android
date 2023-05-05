package com.example.pbbsattendance.compose

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.dto.UserSubjectDto
import com.example.pbbsattendance.R
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.BeforeTagNfcViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity
import kotlinx.coroutines.launch

@Composable
fun BeforeTagNfcScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    attendanceCheckViewModel: BeforeTagNfcViewModel = hiltViewModel()
){
    val user = mainViewModel.getUser()
    val scheduleSubject = mainViewModel.getScheduleSubject()
    attendanceCheckViewModel.getAttendanceDateList(UserSubjectDto(user.id,scheduleSubject.originId))

    val dateList by attendanceCheckViewModel.dateList.observeAsState(initial = emptyList())
    BeforeTagNfcScreen(
        dateList,
        scheduleSubject,
        onStartAttendance = {
            mainViewModel.setLectureTimeItem(it)
            navController.navigate(route = Screen.AfterTagNfc.route)
        }
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BeforeTagNfcScreen(dateList:List<LectureTimeItemModel>, scheduleSubject: ScheduleEntity, onStartAttendance:(LectureTimeItemModel)->Unit = {}) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var isLectureTimeSelected by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    var lectureTime by remember {
        mutableStateOf(LectureTimeItemModel("?","?","?"))
    }
    val onLectureTimeItemSelected = { index:Int ->
        lectureTime = dateList[index]
        isLectureTimeSelected = true
    }
    val context = LocalContext.current


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
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(isLectureTimeSelected){
                    Text(text = lectureTime.week + "주차" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    Text(text = lectureTime.time + "차시" ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                    Text(text = lectureTime.date ,style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
                }
                IconButton(
                    onClick = {scope.launch { state.show() }}
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "")
                }
            }
            Button(
                onClick = { if(isLectureTimeSelected){
                        onStartAttendance(lectureTime)
                    }
                    else{
                        Toast.makeText(context, "출석할 날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue4),
                border = BorderStroke(1.dp, Blue3),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 68.dp, vertical = 36.dp)
                    .height(55.dp)
            ) {
                Text(text = "NFC 인증", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
            }
        }
    }

}

@Preview
@Composable
private fun AttendanceCheckPreview(){
    BeforeTagNfcScreen(
        dateList = listOf(LectureTimeItemModel(time="1", date = "23/04/30",week="9")),
        scheduleSubject = ScheduleEntity(originId = 0, scheduleDay = 30, scheduleName = "캡스톤디자인(2)", roomInfo = "505호", startTime = "10:00", endTime = "11:50")
    )
}
