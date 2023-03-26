package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbbsattendance.ui.theme.*

@Composable
fun LiveStatusView(color1:Color, color2:Color) {
    Row(
        modifier = Modifier.padding(top=20.dp)
    ) {
        RoundLiveCard(number = 18, text = "출석", color = color1)
        RoundLiveCard(number = 6, text = "결석", color = color2)
    }
    Row(
        modifier = Modifier.padding(top=20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ProgressCircle(number = 1, title = "공결")
        ProgressCircle(number = 4, title = "지각")
    }
}

@Composable
fun RoundLiveCard(number:Int, text:String, color: Color){
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
fun LiveStatusViewPreview() {
    LiveStatusView(Blue3, Grey4)
}