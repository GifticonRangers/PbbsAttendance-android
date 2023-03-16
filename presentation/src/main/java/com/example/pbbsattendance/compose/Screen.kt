package com.example.pbbsattendance.compose

sealed class Screen(val route: String) {
    object BeforeStartAttendanceManage: Screen(route = "BeforeStartAttendanceManage")
    object AfterStartAttendanceManage: Screen(route = "AfterStartAttendanceManage")
}