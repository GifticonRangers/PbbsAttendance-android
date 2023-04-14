package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.dto.UserDto
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val repository:LoginRepositoryImpl):ViewModel(){
    private var _checkIdResult = MutableLiveData<Boolean>()
    val checkIdResult:LiveData<Boolean> get() = _checkIdResult
    private var _signupResult = MutableLiveData<UserModel>()
    val signupResult:LiveData<UserModel> get() = _signupResult

    fun checkId(id:String){
        viewModelScope.launch {
            val result = repository.checkId(id)
            when (result){
                true -> _checkIdResult.value = true
                false -> _checkIdResult.value = false
            }
        }
    }

    fun signup(dto: UserDto){
        viewModelScope.launch {
            val result = repository.signup(dto)
            _signupResult.value = result
        }
    }
}