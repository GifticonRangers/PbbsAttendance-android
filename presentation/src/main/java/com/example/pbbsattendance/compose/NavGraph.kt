package com.example.pbbsattendance.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination:String
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = Screen.BeforeStartAttendanceManage.route){
            BeforeStartAttendanceManageScreen(navController = navController)
        }
        composable(route = Screen.AfterStartAttendanceManage.route){
            AfterStartAttendanceManageScreen(navController = navController)
        }
        composable(route = Screen.BeforeTagNfc.route){
            BeforeTagNfcScreen(navController = navController)
        }
        composable(route = Screen.AfterTagNfc.route){
            AfterTagNfcScreen(navController = navController)
        }
    }
}