package com.example.pbbsattendance.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val repository:LoginRepositoryImpl):ViewModel(){
    private var _checkIdResult = MutableLiveData<Boolean>()
    val checkIdResult:LiveData<Boolean> get() = _checkIdResult
    fun checkId(id:String){
        Log.i("id", id)
        viewModelScope.launch {
            val result = repository.checkId(id)
            when (result){
                true -> _checkIdResult.value = true
                false -> _checkIdResult.value = false
            }
        }
    }
}