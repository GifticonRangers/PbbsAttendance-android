package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.*
import com.example.domain.model.User
import com.example.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun getUser(){
        viewModelScope.launch {
            _user.value = getUserUseCase.invoke()
        }
    }
}