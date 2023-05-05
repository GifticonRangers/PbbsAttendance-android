package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.util.INF
import com.example.domain.model.UserModel
import com.example.domain.model.type.GenderUser
import com.example.domain.model.type.TypeUser
import com.example.domain.usecases.GetAuthNfcUseCase
import com.example.pbbsattendance.mapper.LectureMapper
import com.example.pbbsattendance.model.LectureTimeItemModel
import com.example.pbbsattendance.util.nfcPayloadList
import com.islandparadise14.mintable.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAuthNfcUseCase: GetAuthNfcUseCase): ViewModel(){
    private var _selectedLectureTime: MutableStateFlow<LectureTimeItemModel> = MutableStateFlow(
        LectureTimeItemModel("","","")
    )
    val selectedLectureTime: StateFlow<LectureTimeItemModel> get() = _selectedLectureTime
    private var _authNfcResultFlow: MutableStateFlow<String> = MutableStateFlow("")
    val authNfcResultFlow: StateFlow<String> get() = _authNfcResultFlow
    val UNVAILABLE_TAG = "UNVAILABLE_TAG"
    private lateinit var job:Job

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
        private var authNfcResult = ""
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

    fun getAuthNfc(nfcPayloadId:String){
        val dto = LectureMapper.mapToLectureInfoDto(lectureTimeItem, schduleSubject.originId)
        Log.i("MainViewModel.getAuthNfc.dto","${dto}")
        if (nfcPayloadId in nfcPayloadList){
            viewModelScope.launch {
                authNfcResult = getAuthNfcUseCase.invoke(dto)
            }
            Log.i("MainViewModel.getAuthNfc.success::","${authNfcResult}")
        }
        else{
            authNfcResult = UNVAILABLE_TAG
            Log.i("MainViewModel.getAuthNfc.UNVAILABLE_TAG::","${authNfcResult}")
        }
    }

    fun getAuthNfcResultFlow(){
        val refreshIntervalMs:Long = 3000
        Log.i("MainViewModel.getAuthNfcResultFlow.beforeFlow::","${authNfcResult}")
        val authNfcResultFlow: Flow<String> = flow{
            while (true){
                Log.i("MainViewModel.getAuthNfcResultFlow::","${authNfcResult}")
                emit(authNfcResult)
                delay(refreshIntervalMs)
            }
        }
        job = viewModelScope.launch {
            authNfcResultFlow
                .cancellable()
                .collect{
                    _authNfcResultFlow.value = it
                }
        }
    }
    fun stopAuthNfcResultFlow(){
        job.cancel()
    }
}