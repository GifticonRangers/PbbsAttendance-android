package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dto.TokenResponse
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.model.UserModel
import com.example.domain.model.dto.LoginDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepositoryImpl): ViewModel() {
    private var _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun login(dto: LoginDto){
        viewModelScope.launch {
            val result = repository.login(dto)
            _loginResult.value = result
        }
    }
}