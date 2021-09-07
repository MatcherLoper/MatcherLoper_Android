package com.matchloper.util

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.navigation.findNavController
import com.matchloper.R
import com.matchloper.SingleTon
import com.matchloper.data.*
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
            Log.e("room",roomPositionList.toString())
            val requestBody = RoomCreateRequestData(name.toString(),possibleOfflineArea.toString(),roomPositionList,userId)
            RetrofitBuilder.networkService.createRoom(requestBody).enqueue(object :
                Callback<DefaultResponseData> {
                override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {
                    Log.e("error",t.message)
                }

                override fun onResponse(
                    call: Call<DefaultResponseData>,
                    response: Response<DefaultResponseData>
                ) {
                    val res = response.body()
                    Log.e("ress",response.raw().toString())
                    val message = res?.message
                    Log.e("res",res.toString())
                    if(message == null) (it.context as Activity).finish()
                    else if(message == "user already have another open room") Toast.makeText(button.context,
                        "현재 아이디로 이미 개설된 방이 있습니다", Toast.LENGTH_SHORT).show()

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

    @BindingAdapter("joinRoom")
    @JvmStatic
    fun join(button: Button, position : String?) {
        if(position != null && position != "") {

            val requestBody = RequestPositionData(position)

            button.setOnClickListener {
                RetrofitBuilder.networkService.joinRoom(requestBody,SingleTon.prefs.userId).enqueue(object : Callback<DefaultResponseData>{
                    override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DefaultResponseData>,
                        response: Response<DefaultResponseData>
                    ) {
                        val res = response.body()
                        Log.e("res",res.toString())
                        when(res?.message) {
                            null -> button.findNavController().navigate(R.id.action_navigation_matching_dialog_to_navigation_participation)
                            "There are no rooms available to participate" -> Toast.makeText(button.context,"참여할 수 있는 방이 없습니다",Toast.LENGTH_SHORT).show()
                            "User can't join room. user role: OWNER" -> Toast.makeText(button.context,"방장은 다른 방에 참여할 수 없습니다.",Toast.LENGTH_SHORT).show()
                            "User can't join room. user role: MATCHING" -> Toast.makeText(button.context,"이미 매칭에 참여하고 있습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    @BindingAdapter("cancel")
    @JvmStatic
    fun cancel(button: Button, position: String?) {
        val matchingDialogFragment = MatchingDialogFragment()
        button.setOnClickListener {

//            button.findNavController().navigate(R.id.action_navigation_matching_dialog_to_navigation_participation)
        }
    }
}