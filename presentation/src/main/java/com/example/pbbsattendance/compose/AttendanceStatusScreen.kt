package com.example.pbbsattendance.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.dummyData.AttendanceStatus
import com.example.pbbsattendance.dummyData.Lecture
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.colorMapper

@Composable
fun AttendanceStatusScreen() {
    Column(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        LectureTitle(title = "물류의 이해")
        LazyColumn{
            itemsIndexed(
                AttendanceStatus().body //나중엔 데이터로
            ){ index, item ->  
                StatusCard(item)
            }
        }
    }
}

@Composable
fun StatusCard(data: Lecture){
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = data.classOrder.toString(), style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
                Text(text = "차시", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Black1)
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = data.status, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp), color = Blue2)
                Canvas(modifier = Modifier
                    .size(26.dp)
                    .padding(horizontal = 8.dp), onDraw = { drawCircle(color = colorMapper(data.status))})
            }
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


@Preview
@Composable
private fun AttendanceStatusPreview(){
    AttendanceStatusScreen()
}