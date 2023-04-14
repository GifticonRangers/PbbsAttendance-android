package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.*
import com.example.domain.model.UserModel
import com.example.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> = _user

    fun getUser(){
        viewModelScope.launch {
            _user.value = getUserUseCase.invoke()
        }
    }
}