package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.Blue3
import com.example.pbbsattendance.ui.theme.Grey
import com.example.pbbsattendance.ui.theme.suit_medium
import com.example.pbbsattendance.ui.theme.suit_regular
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun StudentCountAndLectureTimeBar( course: Int, modalState: ModalBottomSheetState){
    val scope = rememberCoroutineScope()
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row() {
            Text(text = "학생", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start = 10.dp))
            Text(text = "24", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Blue3, modifier = Modifier.padding(start = 5.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="${course}차시", style = TextStyle(fontFamily = suit_medium, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
            IconButton(
                onClick = {scope.launch { modalState.show() }}
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun StudentAndLectureCourseBarPreview(){
    StudentCountAndLectureTimeBar(course = 1, modalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden))
}