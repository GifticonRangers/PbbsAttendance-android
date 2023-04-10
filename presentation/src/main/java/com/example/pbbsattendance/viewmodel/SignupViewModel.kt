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

    fun checkId(id:String){
        Log.i("id", id)
        viewModelScope.launch {
            val result = repository.checkId(id)
            when(result){
                true -> Log.i("id중복 통과", result.toString())
                else -> Log.i("id중복 실패", result.toString())
            }
        }
    }
}