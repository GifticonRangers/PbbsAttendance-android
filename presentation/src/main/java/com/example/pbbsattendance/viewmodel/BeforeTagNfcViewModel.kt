package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.dto.UserSubjectDto
import com.example.domain.usecases.GetAttendanceDateListUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeforeTagNfcViewModel @Inject constructor(
    private val getAttendanceDateListUseCase: GetAttendanceDateListUseCase
):ViewModel(){
    private var _dateList: MutableLiveData<List<LectureTimeItemModel>> = MutableLiveData()
    val dateList : LiveData<List<LectureTimeItemModel>> get() = _dateList

    fun getAttendanceDateList(dto: UserSubjectDto){
        val result = arrayListOf<LectureTimeItemModel>()
        viewModelScope.launch{
            getAttendanceDateListUseCase.invoke(dto).forEach {
                result.add(LectureMapper.mapperToLectureDate(it))
            }
            _dateList.value = result
        }
    }
}