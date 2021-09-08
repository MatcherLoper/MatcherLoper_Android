package com.matchloper.util

import android.widget.Button
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.matchloper.data.DefaultResponseData
import com.matchloper.network.RetrofitBuilder
import com.matchloper.view.MatchActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserInfoBindingAdapter {

    @BindingAdapter("withdrawal")
    @JvmStatic
    fun withdrawal(button : Button, userId : Int) {
        button.setOnClickListener {
            RetrofitBuilder.networkService.deleteUser(userId).enqueue(object : Callback<DefaultResponseData>{
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()
                    when(res?.message) {
                        null -> {
                            Toast.makeText(button.context, "성공적으로 탈퇴되었습니다", Toast.LENGTH_SHORT)
                                .show()
                            (button.context as MatchActivity).finish()
                        }
                        else -> Toast.makeText(button.context, "탈퇴에 실패하였습니다", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }
}