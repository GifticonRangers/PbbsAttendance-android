package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetStudentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceManageViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase
): ViewModel(){
    fun onStart() {
        // start task - the composable has entered the composition
        Log.i("AttendanceManageViewModel.onStart","the composable has entered the composition")
    }

    fun onStop() {
        // cancel task - the composable has left the composition
        Log.i("AttendanceManageViewModel.onStop","the composable has left the composition")
    }
}