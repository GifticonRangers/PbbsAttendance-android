package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.Grey
import com.example.pbbsattendance.ui.theme.Grey2
import com.example.pbbsattendance.ui.theme.suit_regular

//@Composable
//fun StudentWithSwitchCard(data: Student) {
//    Row(
//        Modifier
//            .padding(bottom = 5.dp)
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(painter = painterResource(id = R.drawable.ic_student), contentDescription = "")
//            Text(text = data.name, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp)  )
//            Text(text = data.studentId, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2, modifier = Modifier.padding(start=5.dp) )
//        }
//        Row(
//        ) {
//            //Image(painter = painterResource(id = attendanceImageMaaper(data.isAttendance)), contentDescription = "")
//        }
//    }
//}
//
//@Preview
//@Composable
//fun StudentWithSwitchCardPreview() {
//    StudentWithSwitchCard(data = Student(name = "김문기", studentId = "20202001762", attendanceState = "출석완료"))
//}