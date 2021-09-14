package com.matchloper.viewModel

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val userId = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val deviceToken = MutableLiveData<String>()

    val userEmail = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userSignUpPw = MutableLiveData<String>()
    val userPhoneNumber = MutableLiveData<String>()
    val userAddress = MutableLiveData<String>()
    val userDetailAddress = MutableLiveData<String>()
    val userPosition = MutableLiveData<String>()
    val userSkillInfo = MutableLiveData<String>()
    val userIntroduction = MutableLiveData<String>()

    val positionSpinnerClick = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            if (p0?.getItemAtPosition(p2).toString() != "포지션 선택") userPosition.value =
                when (p0?.getItemAtPosition(p2).toString()) {
                    "백엔드" -> "BACKEND"
                    "프론트엔드" -> "FRONTEND"
                    "안드로이드" -> "ANDROID"
                    "iOS" -> "IOS"
                    else -> ""
                }
        }

    }
}