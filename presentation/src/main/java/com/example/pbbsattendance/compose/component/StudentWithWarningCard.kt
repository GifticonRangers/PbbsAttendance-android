package com.example.pbbsattendance.compose.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AttendantModel
import com.example.domain.model.UserModel
import com.example.domain.model.type.AttendanceState
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.R
import com.example.pbbsattendance.ui.theme.*
import com.example.pbbsattendance.util.isAttendance

@Composable
fun StudentWithWaringCard(data: UserModel) {
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
            Text(text = data.nameUser?:"", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 12.sp), color = Grey, modifier = Modifier.padding(start=5.dp)  )
            Text(text = data.idUser?:"", style = TextStyle(fontFamily = suit_regular, fontWeight = FontWeight.W500, fontSize = 10.sp), color = Grey2, modifier = Modifier.padding(start=5.dp) )
        }
        Row(
        ) {
            Image(painter = painterResource(id = R.drawable.ic_warning), contentDescription = null, modifier = Modifier.padding(end = 10.dp).size(20.dp))
        }
    }
}

@Preview
@Composable
fun StudentWithWaringCardPreview() {
    StudentWithWaringCard(data = UserModel(0,"202001488",
        TypeUser.STUDENT,"김문기","","","",
        GenderUser.FEMALE))
}