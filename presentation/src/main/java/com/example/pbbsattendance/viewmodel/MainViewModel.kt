package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.ViewModel
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
class MainViewModel (): ViewModel(){

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

    fun setScheduleSubject(data: ScheduleEntity){
        schduleSubject = data
    }

    fun getScheduleSubject():ScheduleEntity{
        return schduleSubject
    }
}