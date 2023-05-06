package com.example.pbbsattendance.compose.component

import android.util.Log
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
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.AttendantModel
import com.example.domain.model.UserModel
import com.example.domain.model.type.AttendanceState
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.isAttendance

@Composable
fun StudentWithSwitchCard(data: AttendantModel) {
    Log.i("StudentWithSwitchCard.data::","${data.state.toString()},,,,${data.state!!.state.toString()}")
    val checkedSwitch = isAttendance(data.state.toString())
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
            Text(text = data.name?:"", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp)  )
            Text(text = data.idUser?:"", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2, modifier = Modifier.padding(start=5.dp) )
        }
        Row(
        ) {
            Switch(
                checked = checkedSwitch,
                onCheckedChange = {checkedSwitch},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Blue3,
                    checkedTrackColor = Blue4,
                    uncheckedThumbColor = Grey2,
                    uncheckedTrackColor = Grey,
                ),
                enabled = false
            )

        }
    }
}

@Preview
@Composable
fun StudentWithSwitchCardPreview() {
    StudentWithSwitchCard(data = AttendantModel(3,"202001541","이용인",AttendanceState.ATTENDANCE))
}