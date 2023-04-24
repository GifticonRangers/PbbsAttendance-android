package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.ViewModel
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel(): ViewModel(){

    companion object{
        private var schduleSubject = ScheduleEntity(
            0,
            "",
            "",
            0,
            "",
            ""
        )
    }
    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setScheduleSubject(data: ScheduleEntity){
        schduleSubject = data
    }

    fun getScheduleSubject():ScheduleEntity{
        return schduleSubject
    }
}