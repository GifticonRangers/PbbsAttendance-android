package com.example.pbbsattendance.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pbbsattendance.compose.SetUpNavGraph
import com.example.pbbsattendance.viewmodel.AttendanceManageViewModel

class AttendanceManageFragment:Fragment() {

    lateinit var navHostController: NavHostController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                navHostController = rememberNavController()
                SetUpNavGraph(navController = navHostController)
            }
        }
    }
}