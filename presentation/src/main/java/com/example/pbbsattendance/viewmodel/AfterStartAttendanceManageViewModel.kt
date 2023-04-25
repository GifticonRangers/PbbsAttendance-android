package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.usecases.GetAttendanceDateListUseCase
import com.example.domain.usecases.GetStudentListUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterStartAttendanceManageViewModel @Inject constructor(
    private val getAttendanceDateListUseCase: GetAttendanceDateListUseCase,
    private val getStudentListUseCase: GetStudentListUseCase
): ViewModel(){
    private var _dateList: MutableLiveData<List<LectureTimeItemModel>> = MutableLiveData()
    val dateList : LiveData<List<LectureTimeItemModel>> get() = _dateList
    private var _studentList: MutableLiveData<List<UserModel>> = MutableLiveData()
    val studentList : LiveData<List<UserModel>> get() = _studentList

    fun getAttendanceDateList(dto:UserSubjectDto){
        val result = arrayListOf<LectureTimeItemModel>()
        viewModelScope.launch{
            getAttendanceDateListUseCase.invoke(dto).forEach {
                result.add(LectureMapper.mapperToLectureDate(it))
            }
            _dateList.value = result
        }
    }

    fun getStudentList(dto: IdDto){
        viewModelScope.launch {
            getStudentListUseCase.invoke(dto).let {
                _studentList.value = it
            }
        }
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