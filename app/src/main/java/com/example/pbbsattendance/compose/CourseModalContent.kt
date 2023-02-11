package com.example.pbbsattendance.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun CourseModalContent(modalState:ModalBottomSheetState){
    val scope = rememberCoroutineScope()

    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier.background(color = Color.Black.copy(alpha = 0.4f))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(top = 20.dp, bottom = 8.dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxWidth(),
            ){
                Text(text = "수업 차시 안내", style = TextStyle(fontFamily = suit_semibold, fontSize = 16.sp, color = Grey), modifier = Modifier.align(Alignment.Center))
                IconButton(
                    onClick = {scope.launch { modalState.hide() }},
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "")
                }
            }
            LazyColumn{
                itemsIndexed(
                    listOf("1차시", "2차시", "3차시")
                ){ index, item ->
                    CourseItem(course = item, date = "23/01/19")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseItem(course:String, date:String) {
    var cardColor by remember { mutableStateOf(Grey7) }

    Card(
        border = BorderStroke(width = 1.dp, color = cardColor),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(vertical = 8.dp),
        onClick = { cardColor = Blue3 }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 13.dp),
        ){
            Text(text = course, modifier = Modifier.align(Alignment.Center), style = TextStyle(fontFamily = suit_medium, fontSize = 14.sp))
            Text(text = date, modifier = Modifier.align(Alignment.CenterEnd), style = TextStyle(fontFamily = suit_regular, fontSize = 8.sp))
        }
    }
}

