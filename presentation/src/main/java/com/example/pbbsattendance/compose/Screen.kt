package com.example.pbbsattendance.compose

sealed class Screen(val route: String) {
    object AttendanceManageScreen: Screen(route = "AttendanceManageScreen")
    object BeforeStartAttendance: Screen(route = "BeforeStartAttendance")
    object AfterStartAttendance: Screen(route = "AfterStartAttendance")
}