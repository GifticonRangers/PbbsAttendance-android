package com.example.pbbsattendance.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminViewModel:ViewModel() {
    private val _nfcPayload: MutableLiveData<ArrayList<String>> = MutableLiveData()
    val nfcPayload: LiveData<ArrayList<String>> = _nfcPayload

    fun addNfcPayloadList(payload:String){
        _nfcPayload.value?.add(payload)
    }
}