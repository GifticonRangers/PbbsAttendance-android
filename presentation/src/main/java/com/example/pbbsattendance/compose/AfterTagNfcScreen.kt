package com.example.pbbsattendance.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.example.pbbsattendance.ui.theme.Blue1
import com.example.pbbsattendance.ui.theme.suit_regular
import com.example.pbbsattendance.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun AfterTagNfcScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
){
    val lifecycleOwner = LocalLifecycleOwner.current

    val flowLifecycleAware = remember(mainViewModel.authNfcResultFlow,lifecycleOwner) {
        mainViewModel.authNfcResultFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val authNfcResult by flowLifecycleAware.collectAsState(initial = "")

    LaunchedEffect(authNfcResult){
        mainViewModel.getAuthNfcResultFlow()
        Log.i("AfterTagNfcScreen.LaunchedEffect", "${authNfcResult}")
    }

    AfterTagNfcScreens(authNfcResult, onStopNfcResultFlow = {mainViewModel.stopAuthNfcResultFlow()})
}

@Composable
fun AfterTagNfcScreens(authNfcResult:String, onStopNfcResultFlow:()->Unit){
    if(authNfcResult == "201"){
        NfcAuthSuccessView()
        onStopNfcResultFlow()
    }
    if(authNfcResult == "200"){
        NfcAuthTimeOutView()
        onStopNfcResultFlow()
    }
    if(authNfcResult == "UNVAILABLE_TAG"){
        NotAllowedNfcTagView()
    }
    else{
        NfcTagOrderView()
    }
}
@Composable
fun NfcTagOrderView(){
    Text(
        text = "휴대폰으로 nfc태그를 읽어주세요",
        style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp),
        color = Blue1
    )
}

@Composable
fun NfcAuthSuccessView(){
    Text(
        text = "nfc 출석이 완료되었습니다",
        style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp),
        color = Blue1
    )
}

@Composable
fun NfcAuthTimeOutView(){
    Text(
        text = "nfc 출석이 마감되었습니다",
        style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp),
        color = Blue1
    )
}
@Composable
fun NotAllowedNfcTagView(){
    Column(
        Modifier
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "사용가능한 nfc가 아닙니다",
            style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 14.sp),
            color = Blue1
        )

    }
}

@Preview
@Composable
fun AfterTagNfcScreenPreview(){
    AfterTagNfcScreens("", onStopNfcResultFlow = {})
}
