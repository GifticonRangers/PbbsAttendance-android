package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.ui.theme.*

@Composable
fun AttendanceManageModalContent(){
    Column {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "출석 관리",
                        style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.Bold, fontSize = 16.sp),
                        color = Indigo,
                    )

                }
            },
            text = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("김진 202001541", style = TextStyle(fontFamily = suit_medium, fontWeight = FontWeight.Medium, fontSize = 8.sp), color = Grey )
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    border = BorderStroke(1.dp, Blue5),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .height(33.dp),
                    onClick = { }
                ){
                    Text("출석", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow2),
                    border = BorderStroke(1.dp, Yellow),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .height(33.dp),
                    onClick = {print("")}
                ){
                    Text("지각", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    border = BorderStroke(1.dp, Pink),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .height(33.dp),
                    onClick = {print("")}
                ){
                    Text("결석", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    border = BorderStroke(1.dp, Green),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .height(33.dp),
                    onClick = {print("")}
                ){
                    Text("공결", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
                }
            },
        )
    }
}

@Preview
@Composable
fun AttendanceManageModalContentPreview(){
    AttendanceManageModalContent()
}