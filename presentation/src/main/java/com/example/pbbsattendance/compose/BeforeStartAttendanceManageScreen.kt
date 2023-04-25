package com.example.pbbsattendance.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.dto.UserSubjectDto
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.BeforeStartAttendanceManageViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity
import kotlinx.coroutines.launch

@Composable
fun BeforeStartAttendanceManageScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    beforeStartAttendanceManageViewModel: BeforeStartAttendanceManageViewModel = hiltViewModel(),
) {
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val user = mainViewModel.getUser()
    beforeStartAttendanceManageViewModel.getAttendanceDateList(UserSubjectDto(user.id,scheduleSubject.originId))

    val dateList by beforeStartAttendanceManageViewModel.dateList.observeAsState(initial = emptyList())
    BeforeStartAttendanceManageScreen(
        dateList = dateList,
        scheduleSubject = scheduleSubject,
        onStartAttendance = {
            navController.navigate(route = Screen.AfterStartAttendanceManage.route)
        }
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BeforeStartAttendanceManageScreen(dateList:List<LectureTimeItemModel>, scheduleSubject: ScheduleEntity, onStartAttendance:()->Unit = {}){
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val onLectureTimeItemSelected = { index:Int ->
        /*선택된 차시에 대한 내용을 MainViewModel로 넘기고 다음단계로 넘어가면 된다*/
        Log.i("BeforeStartAttendanceManageScreen","선택된 정보:${dateList[index]}")

        onStartAttendance()
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
            Button(
                onClick = { scope.launch { state.show() } },
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
                    .padding(top = 12.dp, bottom = 12.dp, start = 10.dp)
            ){

            }
        }
    }
}

//@Preview
//@Composable
//private fun BeforeStartAttendanceManagePreview(){
//    BeforeStartAttendanceManageScreen(navController = rememberNavController(),  )
//}