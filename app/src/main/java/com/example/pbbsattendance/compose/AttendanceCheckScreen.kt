package com.example.pbbsattendance.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*

@Composable
fun AttendanceCheckScreen() {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LectureTitle(title = "물류의 이해", id = "XAA8057001")
        LectureCourseBar(course = 1)
        BeforeAttendanceScreen()
    }

}

@Composable
fun BeforeAttendanceScreen() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Blue4),
        border = BorderStroke(1.dp, Blue3),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 78.dp, vertical = 36.dp)
            .height(55.dp)
    ) {
        Text(text = "NFC 인증", style = TextStyle(fontFamily = suit, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
    }
}

@Composable
fun AfterAttendanceScreen() {

}

@Composable
fun LectureTitle(title:String, id:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ){
        Text(text = title, style = TextStyle(fontFamily = suit, fontWeight = FontWeight.W600, fontSize = 16.sp), color = Blue1)
        Text(text = "(${id})", style = TextStyle(fontFamily = suit, fontWeight = FontWeight.W600, fontSize = 16.sp), color = Blue1)
    }
}
@Composable
fun LectureCourseBar(course: Int){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Text(text="${course}차시", Modifier.padding(horizontal = 10.dp), style = TextStyle(fontFamily = suit, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
        Image(painterResource(id = R . drawable . ic_more), contentDescription = "", Modifier.padding(horizontal = 10.dp))
    }
}

@Preview
@Composable
private fun AttendanceCheckPreview(){
    AttendanceCheckScreen()
}
