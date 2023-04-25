package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.UserModel
import com.example.domain.usecases.GetAttendanceTotalInfoUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterStartAttendanceManageViewModel @Inject constructor(
    private val getAttendanceTotalInfoUseCase: GetAttendanceTotalInfoUseCase
): ViewModel(){
    private val _attendanceTotal: MutableLiveData<AttendanceTotalModel> = MutableLiveData()
    val attendanceTotal: LiveData<AttendanceTotalModel> = _attendanceTotal

    fun getAttendanceTotalInfo(lectureTimeItemModel:LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        viewModelScope.launch {
            _attendanceTotal.value = getAttendanceTotalInfoUseCase.invoke(dto)
        }
    }

}