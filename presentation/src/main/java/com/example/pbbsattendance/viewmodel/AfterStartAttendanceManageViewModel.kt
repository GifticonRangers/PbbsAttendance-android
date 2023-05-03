package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.UserModel
import com.example.domain.usecases.GetAttendanceTotalInfoUseCase
import com.example.domain.usecases.GetNfcEndUseCase
import com.example.domain.usecases.GetNfcStartUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterStartAttendanceManageViewModel @Inject constructor(
    private val getAttendanceTotalInfoUseCase: GetAttendanceTotalInfoUseCase,
    private val getNfcStartUseCase: GetNfcStartUseCase,
    private val getNfcEndUseCase: GetNfcEndUseCase
): ViewModel(){
    private val _attendanceTotal: MutableLiveData<AttendanceTotalModel> = MutableLiveData()
    val attendanceTotal: LiveData<AttendanceTotalModel> = _attendanceTotal
    private var _startNfcTagResponseCode: MutableLiveData<String> = MutableLiveData()
    val startNfcTagResponseCode: LiveData<String> get() = _startNfcTagResponseCode
    private var _endNfcTagResponseCode: MutableLiveData<String> = MutableLiveData()
    val endNfcTagResponseCode: LiveData<String> get() = _endNfcTagResponseCode

    fun getAttendanceTotalInfo(lectureTimeItemModel:LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        viewModelScope.launch {
            _attendanceTotal.value = getAttendanceTotalInfoUseCase.invoke(dto)
        }
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