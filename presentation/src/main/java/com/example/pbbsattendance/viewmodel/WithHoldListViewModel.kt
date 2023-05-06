package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceTotalModel
import com.example.domain.model.UserBriefModel
import com.example.domain.model.dto.IdAttendanceStateDto
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.usecases.GetAttendanceDateListUseCase
import com.example.domain.usecases.GetAttendanceTotalInfoUseCase
import com.example.domain.usecases.GetHoldAttendanceUseCase
import com.example.domain.usecases.UpdateAttendanceUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.mapper.UserMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.model.UserNewStateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithHoldListViewModel @Inject constructor(
    private val getAttendanceTotalInfoUseCase: GetAttendanceTotalInfoUseCase,
    private val getAttendanceDateListUseCase: GetAttendanceDateListUseCase,
    private val getHoldAttendanceUseCase: GetHoldAttendanceUseCase,
    private val updateAttendanceUseCase: UpdateAttendanceUseCase
): ViewModel(){
    private val _attendanceTotal: MutableLiveData<AttendanceTotalModel> = MutableLiveData()
    val attendanceTotal: LiveData<AttendanceTotalModel> = _attendanceTotal
    private var _dateList: MutableLiveData<List<LectureTimeItemModel>> = MutableLiveData()
    val dateList : LiveData<List<LectureTimeItemModel>> get() = _dateList
    private var _holdList: MutableLiveData<List<UserBriefModel>> = MutableLiveData()
    val holdList : LiveData<List<UserBriefModel>> get() = _holdList
    private var _selectedDate: MutableLiveData<LectureTimeItemModel> = MutableLiveData()
    val selectedDate: LiveData<LectureTimeItemModel> get() = _selectedDate

    fun getHoldAttendanceList(lectureTimeItemModel: LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        viewModelScope.launch {
            _holdList.value = getHoldAttendanceUseCase.invoke(dto)
        }
    }

    fun getAttendanceTotalInfo(lectureTimeItemModel: LectureTimeItemModel, idSubject:Int){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItemModel, idSubject)
        _selectedDate.value = lectureTimeItemModel
        viewModelScope.launch {
            _attendanceTotal.value = getAttendanceTotalInfoUseCase.invoke(dto)
            Log.i("WithHoldListViewModel.getAttendanceTotalInfo.Result::","${dto.weekAttendance}주차${dto.timeAttendance}차시:${_attendanceTotal.value}")
        }
    }

    fun getAttendanceDateList(dto: UserSubjectDto){
        val result = arrayListOf<LectureTimeItemModel>()
        viewModelScope.launch{
            getAttendanceDateListUseCase.invoke(dto).forEach {
                result.add(LectureMapper.mapperToLectureDate(it))
            }
            _dateList.value = result
        }
    }

    fun updateAttendance(param:UserNewStateModel){
        val dto = UserMapper.mapToIdAttendanceFromUserNewState(param)
        viewModelScope.launch {
            updateAttendanceUseCase.invoke(dto)
        }
    }
}