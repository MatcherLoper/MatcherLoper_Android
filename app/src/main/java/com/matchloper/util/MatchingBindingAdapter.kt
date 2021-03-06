package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import com.matchloper.data.DefaultResponseData
import com.matchloper.data.RoomCreateRequestData
import com.matchloper.data.RoomPosition
import com.matchloper.network.RetrofitBuilder
import com.matchloper.view.MatchActivity
import com.matchloper.view.MatchOpenDialogActivity
import com.matchloper.view.MatchingDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MatchingBindingAdapter {

    @BindingAdapter("matchingOpen")
    @JvmStatic
    fun matchingOpen(button : Button, userId : Int) {
        button.setOnClickListener {
            val intent = Intent(button.context, MatchOpenDialogActivity::class.java)
            button.context.startActivity(intent)
        }
    }

    @BindingAdapter("name", "possibleOfflineArea", "roomPositionList", "userId")
    @JvmStatic
    fun createRoom(button: Button, name : String?, possibleOfflineArea : String?, roomPositionList : ObservableArrayList<RoomPosition>, userId : Int) {
        button.setOnClickListener {
            val requestBody = RoomCreateRequestData(name.toString(),possibleOfflineArea.toString(),roomPositionList,userId)
            RetrofitBuilder.networkService.createRoom(requestBody).enqueue(object :
                Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()

                    when(res?.message) {
                        null -> (it.context as Activity).finish()
                        "user already have another open room" -> Toast.makeText(button.context,
                            "?????? ???????????? ?????? ????????? ?????? ????????????", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    @BindingAdapter("randomMatching")
    @JvmStatic
    fun matching(button: Button, userId: Int) {
        button.setOnClickListener {
            val dialog = MatchingDialogFragment()
            dialog.show((it.context as MatchActivity).supportFragmentManager,"test")
        }
    }
}