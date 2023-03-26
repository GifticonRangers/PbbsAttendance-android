package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.StudentList
import com.example.domain.usecases.GetStudentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceManageViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase
): ViewModel(){
    private val _studentList: MutableLiveData<StudentList> = MutableLiveData()
    val studentList : LiveData<StudentList> = _studentList

    fun getStudentList(){
        viewModelScope.launch {
            _studentList.value = getStudentListUseCase.invoke()
        }
    }
}