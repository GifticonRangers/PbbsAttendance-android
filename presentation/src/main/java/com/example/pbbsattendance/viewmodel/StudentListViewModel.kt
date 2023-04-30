package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserModel
import com.example.domain.model.dto.IdDto
import com.example.domain.usecases.GetStudentListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val getStudentListUseCase: GetStudentListUseCase
):ViewModel() {
    private var _studentList: MutableLiveData<List<UserModel>> = MutableLiveData()
    val studentList : LiveData<List<UserModel>> get() = _studentList

    fun getStudentList(dto:IdDto){
        val result = arrayListOf<UserModel>()
        viewModelScope.launch {
            result.addAll(getStudentListUseCase.invoke(dto))
            _studentList.value = result.toList()
        }
    }
}