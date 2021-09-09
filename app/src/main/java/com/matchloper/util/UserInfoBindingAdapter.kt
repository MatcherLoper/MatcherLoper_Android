package com.matchloper.util

import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.matchloper.R
import com.matchloper.data.DefaultResponseData
import com.matchloper.data.UserInfoData
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

    @BindingAdapter("update")
    @JvmStatic
    fun infoUpdate(button: Button, userInfo : UserInfoData?) {
        button.setOnClickListener {
            button.findNavController().navigate(R.id.action_navigation_my_info_to_navigation_user_info_update, bundleOf("userInfo" to userInfo))
        }
    }

    @BindingAdapter("cancelUpdate")
    @JvmStatic
    fun cancelUpdate(button: Button, userId: Int) {
        button.setOnClickListener {
            button.findNavController().navigate(R.id.action_navigation_user_info_update_to_navigation_my_info)
        }
    }
}