package com.matchloper.ui.myinfo

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
}