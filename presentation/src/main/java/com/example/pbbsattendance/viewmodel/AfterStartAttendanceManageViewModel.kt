package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.usecases.GetAttendanceDateList
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterStartAttendanceManageViewModel @Inject constructor(
    private val getAttendanceDateList: GetAttendanceDateList
): ViewModel(){

    fun getAttendanceDateList(dto:UserSubjectDto):ArrayList<LectureTimeItemModel>{
        var result = arrayListOf<LectureTimeItemModel>()
        viewModelScope.launch{
            getAttendanceDateList.invoke(dto).forEach {
                result.add(LectureMapper.mapperToLectureDate(it))
            }
        }
        return result
    }

    fun onStart() {
        // start task - the composable has entered the composition
        Log.i("AttendanceManageViewModel.onStart","the composable has entered the composition")
    }

    fun onStop() {
        // cancel task - the composable has left the composition
        Log.i("AttendanceManageViewModel.onStop","the composable has left the composition")
    }
}