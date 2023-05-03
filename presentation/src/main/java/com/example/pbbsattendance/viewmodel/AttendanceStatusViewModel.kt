package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AttendanceHistoryModel
import com.example.domain.model.dto.LectureInfoDto
import com.example.domain.model.dto.StudentSubjectDto
import com.example.domain.model.type.AttendanceState
import com.example.domain.usecases.GetLiveAttendanceStatusUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceStatusViewModel @Inject constructor(private val getAttendanceStatusUseCase: GetLiveAttendanceStatusUseCase):ViewModel(){
    private var _attendanceStatus:
            MutableStateFlow<AttendanceHistoryModel> = MutableStateFlow(
        AttendanceHistoryModel("","","",AttendanceState.NULL,0,0,0)
    )
    val attendanceStatus: StateFlow<AttendanceHistoryModel> get() = _attendanceStatus
    private lateinit var job:Job

    fun collectAttendanceStatus(dto:StudentSubjectDto, lectureInfoDto: LectureTimeItemModel){
        val lectureData = LectureMapper.mapToLectureInfoDto(lectureInfoDto,dto.idSubject)
        job = viewModelScope.launch {
            getAttendanceStatusUseCase.invoke(dto)
                .cancellable()
                .map {
                    it.filter {
                        it.week == lectureData.weekAttendance && it.time == lectureData.timeAttendance
                    }
                }
                .collect{
                    //week, time이 lectureData와 같은 경우는 한 가지 없음
                    _attendanceStatus.value = it[0]
                }
        }
    }
}