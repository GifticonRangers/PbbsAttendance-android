package com.example.pbbsattendance.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.StudentCard
import com.example.pbbsattendance.ui.theme.Grey4
import com.example.pbbsattendance.viewmodel.MainViewModel
import com.example.pbbsattendance.viewmodel.StudentListViewModel
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun StudentListScreen(
    studentListViewModel: StudentListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
){
    var scheduleSubject:ScheduleEntity
    var studentList = arrayListOf<UserModel>()

    scheduleSubject = mainViewModel.getScheduleSubject()
    LaunchedEffect(key1 = scheduleSubject){
        studentList = studentListViewModel.getStudentList(IdDto(scheduleSubject.originId))
    }

    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title =scheduleSubject.scheduleName)
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