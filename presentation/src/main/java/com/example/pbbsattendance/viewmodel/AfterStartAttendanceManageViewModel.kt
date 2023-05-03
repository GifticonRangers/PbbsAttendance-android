package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.usecases.GetLiveAttendanceTotalInfoUseCase
import com.example.domain.usecases.GetNfcEndUseCase
import com.example.domain.usecases.GetNfcStartUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterStartAttendanceManageViewModel @Inject constructor(
    private val getAttendanceTotalInfoUseCase: GetLiveAttendanceTotalInfoUseCase,
    private val getNfcStartUseCase: GetNfcStartUseCase,
    private val getNfcEndUseCase: GetNfcEndUseCase,
): ViewModel(){
    private val _attendanceTotal: MutableStateFlow<AttendanceTotalModel> = MutableStateFlow(
        AttendanceTotalModel(0,0,0,0)
    )
    val attendanceTotal: StateFlow<AttendanceTotalModel> = _attendanceTotal
    private var _startNfcTagResponseCode: MutableLiveData<String> = MutableLiveData()
    val startNfcTagResponseCode: LiveData<String> get() = _startNfcTagResponseCode
    private var _endNfcTagResponseCode: MutableLiveData<String> = MutableLiveData()
    val endNfcTagResponseCode: LiveData<String> get() = _endNfcTagResponseCode
    private lateinit var job:Job

    fun collectLiveAttendanceTotalInfo(lectureTimeItemModel:LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        job = viewModelScope.launch {
            getAttendanceTotalInfoUseCase.invoke(dto)
                .cancellable()
                .collect{
                    _attendanceTotal.value = it
                }
            }
    }

    fun stopLiveAttendanceTotalInfo(){
        job.cancel()
    }

    fun startNfcTag(lectureTimeItemModel:LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        Log.i("startNfcTag::Request","dto.weekAttendance:${dto.weekAttendance}, dto.timeAttendance:${dto.timeAttendance}, dto.idSubject:${dto.idSubject}")
        viewModelScope.launch {
            _startNfcTagResponseCode.value = getNfcStartUseCase.invoke(dto)
            Log.i("startNfcTag::Result","${_startNfcTagResponseCode.value}")
        }
    }

    fun endNfcTag(lectureTimeItemModel:LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        Log.i("endNfcTag::Request","dto.weekAttendance:${dto.weekAttendance}, dto.timeAttendance:${dto.timeAttendance}, dto.idSubject:${dto.idSubject}")
        viewModelScope.launch {
            _endNfcTagResponseCode.value = getNfcEndUseCase.invoke(dto)
            Log.i("endNfcTag::Result","${_endNfcTagResponseCode.value}")
        }
    }
}