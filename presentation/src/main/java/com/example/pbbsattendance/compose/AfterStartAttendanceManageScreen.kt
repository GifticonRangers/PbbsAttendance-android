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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.LiveStatusView
import com.example.pbbsattendance.compose.component.StudentCountAndLectureTimeBar
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.viewmodel.AfterStartAttendanceManageViewModel
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.islandparadise14.mintable.ScheduleEntity
import kotlinx.coroutines.launch

@Composable
fun AfterStartAttendanceManageScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    afterStartAttendanceManageViewModel: AfterStartAttendanceManageViewModel = hiltViewModel(),
) {
    val scheduleSubject = mainViewModel.getScheduleSubject()
    val user = mainViewModel.getUser()

    afterStartAttendanceManageViewModel.getAttendanceDateList(UserSubjectDto(user.id,scheduleSubject.originId))
    afterStartAttendanceManageViewModel.getStudentList(IdDto(scheduleSubject.originId))

    val dateList by afterStartAttendanceManageViewModel.dateList.observeAsState(initial = emptyList())
    val studentList by afterStartAttendanceManageViewModel.studentList.observeAsState(initial = emptyList())

    AfterStartAttendanceManageScreen(
        dateList = dateList,
        studentList = studentList,
        scheduleSubject = scheduleSubject,
        onStartAttendance = {navController.navigate(route = Screen.BeforeStartAttendanceManage.route)}
    )

}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AfterStartAttendanceManageScreen(dateList:List<LectureTimeItemModel>, studentList:List<UserModel>,scheduleSubject:ScheduleEntity, onStartAttendance:()->Unit = {}){
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            LectureTimeModalContent(state,dateList)
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
                onClick = { onStartAttendance() },
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
                itemsIndexed(
                    dateList
                ) { index, item ->
                    Text(text = "모르게따")
                }
            }
        }
    }
}
//@Preview
//@Composable
//fun AfterStartAttendanceManagePreview() {
//    AfterStartAttendanceManageScreen(navController = rememberNavController())
//}