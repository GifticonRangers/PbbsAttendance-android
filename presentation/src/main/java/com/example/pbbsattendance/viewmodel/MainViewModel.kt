package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.util.INF
import com.example.domain.model.UserModel
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel(){
    private var _selectedLectureTime: MutableStateFlow<LectureTimeItemModel> = MutableStateFlow(
        LectureTimeItemModel("","","")
    )
    val selectedLectureTime: StateFlow<LectureTimeItemModel> get() = _selectedLectureTime
    private lateinit var job: Job

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
        private var lectureTimeItem = LectureTimeItemModel("","","")
        private var nfctagPayload = ""
    }
    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setScheduleSubject(data: ScheduleEntity){
        schduleSubject = data
    }

    /** 절대 MainActivity외의 View에서 사용하지 말것 */
    fun setUser(data:UserModel){
        user = data
    }

    /** 절대 MainActivity, BefroeStartAttendanceManageScreen외의 View에서 사용하지 말것 */
    fun setLectureTimeItem(data:LectureTimeItemModel){
        lectureTimeItem = data
    }

    fun setNfcTagPayload(data:String){
        nfctagPayload = data
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

    fun getLectureTimeItemFlow(){
        val lectureTimeFlow: Flow<LectureTimeItemModel> = flow {
            val refreshIntervalMs:Long = 3000
            while (true){
                emit(lectureTimeItem)
                delay(refreshIntervalMs)
            }
        }
        viewModelScope.launch {
            lectureTimeFlow
                .cancellable()
                .collect{
                    _selectedLectureTime.value = it
                }
        }
    }

    fun getNfcTagPayload():String{
        return nfctagPayload
    }
}