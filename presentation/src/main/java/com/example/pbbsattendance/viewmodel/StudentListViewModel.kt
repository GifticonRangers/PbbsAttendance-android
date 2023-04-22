package com.example.pbbsattendance.viewmodel

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

    fun getStudentList(dto:IdDto):ArrayList<UserModel>{
        val result = arrayListOf<UserModel>()
        viewModelScope.launch {
            //result.addAll(getStudentListUseCase.invoke(dto))
        }
        return result
    }
}