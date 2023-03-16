package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.ui.theme.Blue1
import com.example.pbbsattendance.ui.theme.suit_semibold

@Composable
fun LectureTitle(title:String, id:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ){
        Text(
            text = title,
            style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.W600, fontSize = 16.sp,),
            color = Blue1,
        )
        Text(text = "(${id})", style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.W600, fontSize = 16.sp), color = Blue1)
    }
}