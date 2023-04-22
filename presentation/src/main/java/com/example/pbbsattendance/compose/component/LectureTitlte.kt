package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.ui.theme.Blue1
import com.example.pbbsattendance.ui.theme.Grey3
import com.example.pbbsattendance.ui.theme.Grey4
import com.example.pbbsattendance.ui.theme.suit_semibold

@Composable
fun LectureTitle(title:String?, id:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ){
        Text(
            text = title?:"",
            style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.W600, fontSize = 16.sp,),
            color = Blue1,
        )
        Text(text = "(${id})", style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.W600, fontSize = 16.sp), color = Blue1)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Grey4,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
    ){}
}

@Preview
@Composable
fun LectureTitlePreview() {
    LectureTitle(title = "캡스톤 디자인", id = "XCCDE3243")
}