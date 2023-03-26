package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.domain.model.Student
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.colorMapper

@Composable
fun StudentWithColorSwitchCard(data: Student) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ic_student), contentDescription = "")
                Text(text = data.name, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp)  )
                Text(text = data.studentId, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2, modifier = Modifier.padding(start=5.dp) )
            }
            WarningIcon()
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

@Composable
fun WarningIcon(){
    Icon(painter = painterResource(id = R.drawable.ic_warning), contentDescription = "", tint = Color.Red)
}

@Composable
fun AttendanceStatusView(){
    Row(verticalAlignment = Alignment.CenterVertically){
        Text("출석", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Blue2)
        Canvas(modifier = Modifier
            .size(26.dp)
            .padding(horizontal = 8.dp), onDraw = { drawCircle(color = colorMapper("출석"))})
    }
}

@Preview
@Composable
fun StudentWithColorSwitchCardPreview() {
    StudentWithColorSwitchCard(Student(name = "김문기", studentId = "20202001762", attendanceState = "출석완료"))
}