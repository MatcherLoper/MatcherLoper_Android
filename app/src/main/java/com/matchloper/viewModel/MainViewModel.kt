package com.matchloper.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.data.SignInRequestData

class MainViewModel : ViewModel() {

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val deviceToken = MutableLiveData<String>()



}