package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.util.INF
import com.example.domain.model.UserModel
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel(): ViewModel(){

    companion object{
        private var schduleSubject = ScheduleEntity(
            INF,
            "",
            "",
            0,
            "",
            ""
        )
        private var user = UserModel(
            INF,
            "",
            TypeUser.NULL,
            "",
            "",
            "",
            "",
            GenderUser.NULL
        )
        private var lectureTimeItem = LectureTimeItemModel("","")
    }
    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setScheduleSubject(data: ScheduleEntity){
        schduleSubject = data
    }

    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setUser(data:UserModel){
        user = data
    }

    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setLectureTimeItem(data:LectureTimeItemModel){
        lectureTimeItem = data
    }

    fun getScheduleSubject():ScheduleEntity{
        return schduleSubject
    }

    fun getUser():UserModel{
        return user
    }

    fun getLectureTimeItem():LectureTimeItemModel{
        return lectureTimeItem
    }
}