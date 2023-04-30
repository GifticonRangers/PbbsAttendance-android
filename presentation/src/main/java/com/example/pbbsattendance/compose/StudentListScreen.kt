package com.example.pbbsattendance.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.StudentCard
import com.example.pbbsattendance.ui.theme.Blue3
import com.example.pbbsattendance.ui.theme.Grey
import com.example.pbbsattendance.ui.theme.Grey4
import com.example.pbbsattendance.ui.theme.suit_regular
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.example.pbbsattendance.viewmodel.StudentListViewModel
import com.islandparadise14.mintable.ScheduleEntity
import kotlinx.coroutines.launch

@Composable
fun StudentListScreen(
    studentListViewModel: StudentListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
){
    var scheduleSubject = mainViewModel.getScheduleSubject()
    studentListViewModel.getStudentList(IdDto(scheduleSubject.originId))
    val studentList by studentListViewModel.studentList.observeAsState(initial = emptyList())
    StudentListScreen(studentList, scheduleSubject)
}

@Composable
fun StudentListScreen(studentList:List<UserModel>, scheduleSubject:ScheduleEntity){
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = scheduleSubject.scheduleName)
        Row(
            Modifier.fillMaxWidth()
                .padding(vertical = 10.dp)
                .padding(start = 10.dp)
        ) {
            Text(text = "학생",style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp))
            Text(text = studentList.size.toString(),style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start=5.dp))
        }
        LazyColumn {
            itemsIndexed(
                studentList
            )
            { index, item ->
                StudentCard(data = item)
            }
        }
    }
}

@Preview
@Composable
fun StudentListScreenPreview(){
    StudentListScreen()
}