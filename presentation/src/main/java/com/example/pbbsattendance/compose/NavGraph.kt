package com.example.pbbsattendance.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.BeforeStartAttendanceManage.route){
        composable(route = Screen.BeforeStartAttendanceManage.route){
            BeforeStartAttendanceManageScreen(navController = navController)
        }
        composable(route = Screen.AfterStartAttendanceManage.route){
            AfterStartAttendanceManageScreen(navController = navController)
        }
    }
}