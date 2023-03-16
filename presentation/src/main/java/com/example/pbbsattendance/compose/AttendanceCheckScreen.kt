package com.example.pbbsattendance.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.pbbsattendance.R
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.ui.theme.*
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AttendanceCheckScreen() {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            CourseModalContent(state)
        },
    ) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LectureTitle(title = "물류의 이해", id = "XAA8057001")
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
            LectureCourseBar(course = 1, modalState = state)
            BeforeAttendanceScreen()
        }
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
            .padding(horizontal = 68.dp, vertical = 36.dp)
            .height(55.dp)
    ) {
        Text(text = "NFC 인증", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
    }
}

@Composable
fun AfterAttendanceScreen() {

}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun LectureCourseBar( course: Int, modalState:ModalBottomSheetState) {
    val scope = rememberCoroutineScope()
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text="${course}차시", style = TextStyle(fontFamily = suit_medium, fontWeight = FontWeight.W400, fontSize = 12.sp), color = Grey)
        IconButton(
            onClick = {scope.launch { modalState.show() }}
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "")
        }
    }
}

@Preview
@Composable
private fun AttendanceCheckPreview(){
    AttendanceCheckScreen()
}
