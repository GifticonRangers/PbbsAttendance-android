package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pbbsattendance.compose.component.LectureTitle
import com.example.pbbsattendance.compose.component.StudentCard
import com.example.pbbsattendance.compose.component.StudentCountAndLectureTimeBar
import com.example.pbbsattendance.dummyData.StudentList
import com.example.pbbsattendance.ui.theme.*

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun AfterStartAttendanceManageScreen(navController: NavController) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            LectureTimeModalContent(state)
        },
    ) {
        Column(
            Modifier
                .background(color = Color.White)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LectureTitle(title = "물류의 이해", id = "XAA8057001")
            LiveStatusView()
            Button(
                onClick = { navController.navigate(route = Screen.BeforeStartAttendanceManage.route) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Grey4),
                border = BorderStroke(1.dp, Blue3),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 68.dp, vertical = 36.dp)
                    .height(55.dp)
            ) {
                Text(text = "출석 마감", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W400, fontSize = 16.sp), color = Grey )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = Grey4,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 10f
                        )
                    }
            ){}
            StudentCountAndLectureTimeBar(course = 1, modalState = state)
            LazyColumn {
                itemsIndexed(
                    StudentList().body
                ) { index, item ->
                    StudentCard(item)
                }
            }
        }
    }
}

@Composable
fun LiveStatusView() {
    Row(
        modifier = Modifier.padding(top=45.dp)
    ) {
        RoundLiveCard(number = 18, text = "출석", color = Blue3)
        RoundLiveCard(number = 6, text = "결석", color = Grey4)
    }
    Row(
        modifier = Modifier.padding(top=36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ProgressCircle(number = 1, title = "공결")
        ProgressCircle(number = 4, title = "지각")
    }
}

@Composable
fun RoundLiveCard(number:Int, text:String, color:Color){
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = color,
                spotColor = color,
                shape = RoundedCornerShape(12.dp)
            ),
        border = BorderStroke(width = 0.5.dp, color = color)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 45.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = number.toString(), style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.Bold, fontSize = 24.sp), color = Indigo, modifier = Modifier.padding(top = 5.dp))
            Text(text = text, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.Bold, fontSize = 10.sp), color = Indigo)
        }
    }
}

@Composable
fun ProgressCircle(number: Int, title: String){
    val stroke = with(LocalDensity.current) {
        Stroke(width = ProgressIndicatorDefaults.StrokeWidth.toPx(),
        cap = StrokeCap.Butt)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(70.dp)){
                val diameterOffset = stroke.width
                drawCircle(
                    radius = size.minDimension / 2.0f - diameterOffset,
                    color = Grey3,
                    style = stroke
                )
            }
            CircularProgressIndicator(
                progress = 0.5f,
                modifier = Modifier.then(Modifier.size(70.dp)),
                color = Blue3
            )
            Text(text = number.toString(), style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.Bold, fontSize = 18.sp), color = Indigo)
        }
        Text(text = title, style = TextStyle(fontFamily = suit_semibold, fontWeight = FontWeight.Bold, fontSize = 10.sp), color = Indigo, modifier = Modifier.padding(top = 10.dp))
    }
}

@Preview
@Composable
fun AfterStartAttendanceManagePreview() {
    AfterStartAttendanceManageScreen(navController = rememberNavController())
}