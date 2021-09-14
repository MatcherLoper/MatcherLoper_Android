package com.matchloper.ui.myinfo

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.SingleTon
import com.matchloper.data.UserInfoData
import com.matchloper.data.UserInfoResponseData
import com.matchloper.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder
    val myInfoData = MutableLiveData<UserInfoData>()

    val userName = MutableLiveData<String>()
    val userUpdatePw = MutableLiveData<String>()
    val userPhoneNumber = MutableLiveData<String>()
    val userAddress = MutableLiveData<String>()
    val userDetailAddress = MutableLiveData<String>()
    val userPosition = MutableLiveData<String>()
    val userSkillInfo = MutableLiveData<String>()
    val userIntroduction = MutableLiveData<String>()

    fun getMyInfo() {
        retrofitBuilder.networkService.findUser(SingleTon.prefs.userId).enqueue(object : Callback<UserInfoResponseData> {

            override fun onFailure(call: Call<UserInfoResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<UserInfoResponseData>,
                response: Response<UserInfoResponseData>
            ) {
                val res = response.body()
                if(res?.message == null) myInfoData.value = res?.data

            }
        })
    }

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