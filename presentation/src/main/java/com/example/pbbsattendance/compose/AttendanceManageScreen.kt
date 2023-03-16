package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.pbbsattendance.dummyData.StudentList
import com.example.pbbsattendance.R
import com.example.pbbsattendance.dummyData.Student
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.attendanceImageMaaper

@Composable
fun AttendanceManageScreen(navController: NavHostController){
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = "물류의 이해", id = "XAA8057001")
        BeforeStartAttendance(navController = navController)
        LazyColumn{
            itemsIndexed(
                StudentList().body
            ){ index, item ->
                StudentCard(item)
            }
        }
    }
}

@Composable
fun BeforeStartAttendance(navController: NavController) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Blue4),
        border = BorderStroke(1.dp, Blue3),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 68.dp, vertical = 36.dp)
            .height(55.dp)
            .clickable {
                navController.navigate(route = Screen.AfterStartAttendance.route)
            }
    ) {
        Text(text = "출석 시작", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
    }
}

@Composable
fun StudentCard(data: Student) {
    Row(
        Modifier.padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_student), contentDescription = "")
            Text(text = data.name, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey,  )
            Text(text = data.studentId, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2 )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = attendanceImageMaaper(data.isAttendance)), contentDescription = "")
        }
    }
}

@Composable
fun AfterStartAttendance(navController: NavController) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Grey4),
        border = BorderStroke(1.dp, Blue3),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 68.dp, vertical = 36.dp)
            .height(55.dp)
            .clickable {
                navController.navigate(route = Screen.AfterStartAttendance.route)
            }
    ) {
        Text(text = "출석 마감", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
    }
}

//@Preview
//@Composable
//private fun AttendanceCheckPreview(){
//    AttendanceManageScreen()
//}