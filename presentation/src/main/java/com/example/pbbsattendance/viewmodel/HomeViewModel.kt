package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.data.repository.SubjectRepositoryImpl
import com.example.domain.model.ScheduleSubjectModel
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.usecases.GetUserUseCase
import com.example.pbbsattendance.eventbus.ScheduleSubjectEvent
import com.example.pbbsattendance.mapper.ScheduleMapper
import com.github.tlaabs.timetableview.Schedule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val repository:SubjectRepositoryImpl
): ViewModel() {

    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> = _user

    private var _scheduleSubjectsResult: MutableLiveData<ArrayList<Schedule>> = MutableLiveData()
    val scheduleSubjectsResult: LiveData<ArrayList<Schedule>> = _scheduleSubjectsResult
    private var scheduleArrayList = arrayListOf<Schedule>()
    private var scheduleSubjectArrayList = arrayListOf<ScheduleSubjectModel>()
    fun getUser(dto:IdDto){
        viewModelScope.launch {
            _user.value = getUserUseCase.invoke(dto)
        }
    }

    fun showScheduleSubjects(dto: IdDto){
        viewModelScope.launch {
            Log.i("HomeViewModel::showScheduleSubjects:","send dto to repository")
            scheduleSubjectArrayList = repository.showScheduleSubjects(dto)
            scheduleSubjectArrayList.forEach {
                Log.i("HomeViewModel::showScheduleSubjects::mapper","들어온 데이터 넣는 중")
                val element = ScheduleMapper.mapperToSchedule(it)
                scheduleArrayList.add(element)
            }
            _scheduleSubjectsResult.value = scheduleArrayList
        }
    }

    fun postScheduleSubjectEvent(index:Int){
        EventBus.getDefault().post(ScheduleSubjectEvent(scheduleSubjectArrayList.get(index)))
    }

}