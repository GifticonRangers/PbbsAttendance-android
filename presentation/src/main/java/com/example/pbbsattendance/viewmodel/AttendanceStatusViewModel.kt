package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.usecases.GetAttendanceStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceStatusViewModel @Inject constructor(private val getAttendanceStatusUseCase: GetAttendanceStatusUseCase):ViewModel(){
    private var _statusList: MutableLiveData<List<AttendanceHistoryModel>> = MutableLiveData()
    val statusList : LiveData<List<AttendanceHistoryModel>> get() = _statusList

    fun getAttendanceStatus(dto:LectureInfoDto){
        viewModelScope.launch {
            viewModelScope.launch {
                _statusList.value = getAttendanceStatusUseCase.invoke(dto)
            }
        }
    }
}