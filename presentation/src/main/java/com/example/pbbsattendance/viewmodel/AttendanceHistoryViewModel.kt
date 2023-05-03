package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.usecases.GetAttendanceHistoryUseCase
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceHistoryViewModel @Inject constructor(private val getAttendanceHistoryUseCase: GetAttendanceHistoryUseCase):ViewModel() {
    private var _historyList: MutableLiveData<List<AttendanceHistoryModel>> = MutableLiveData()
    val historyList : LiveData<List<AttendanceHistoryModel>> get() = _historyList

    fun getAttendanceHistory(dto:StudentSubjectDto){
        viewModelScope.launch {
            _historyList.value = getAttendanceHistoryUseCase.invoke(dto)
        }
    }
}