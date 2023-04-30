package com.example.pbbsattendance.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pbbsattendance.ui.theme.Black1
import com.example.pbbsattendance.ui.theme.Blue1
import com.example.pbbsattendance.ui.theme.suit_regular

@Composable
fun AfterTagNfcScreen(navController: NavController){
    AfterTagNfcScreen()
}

@Composable
fun AfterTagNfcScreen(){
    Column(
        Modifier
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "nfc 출석 중입니다...\n강의가 끝날 때까지 폰을 떼지 말고 \nnfc태그 인식을 해주세요",
            style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp),
            color = Blue1
        )
    }
}

@Preview
@Composable
fun AfterTagNfcScreenPreview(){
    AfterTagNfcScreen()
}
