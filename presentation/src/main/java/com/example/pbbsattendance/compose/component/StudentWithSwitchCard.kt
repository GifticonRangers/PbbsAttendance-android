package com.example.pbbsattendance.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.UserModel
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*

@Composable
fun StudentWithSwitchCard(data: UserModel) {
    val checkedSwitch = remember{mutableStateOf(false)}
    Row(
        Modifier
            .padding(bottom = 5.dp)
            .background(color = Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_student), contentDescription = "")
            Text(text = data.nameUser, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp)  )
            Text(text = data.idUser, style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2, modifier = Modifier.padding(start=5.dp) )
        }
        Row(
        ) {
            Switch(
                checked = checkedSwitch.value,
                onCheckedChange = {checkedSwitch.value = it},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Blue3,
                    checkedTrackColor = Blue4,
                    uncheckedThumbColor = Grey2,
                    uncheckedTrackColor = Grey
                )
            )

        }
    }
}

@Preview
@Composable
fun StudentWithSwitchCardPreview() {
    StudentWithSwitchCard(data = UserModel(0,"202001488",TypeUser.STUDENT,"김문기,","01076867103","000lyi@inu.ac.kr","컴퓨터공학부", GenderUser.MALE))
}