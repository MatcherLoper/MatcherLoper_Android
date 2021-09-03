package com.matchloper.util

import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.matchloper.R
import com.matchloper.SingleTon
import com.matchloper.data.DefaultResponseData
import com.matchloper.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RoomBindingAdapter {


    @BindingAdapter("deleteRoomId", "deleteCreateUserId")
    @JvmStatic
    fun delete(button: Button, deleteRoomId : Int, deleteCreateUserId : Int) {
        if(SingleTon.prefs.userId == deleteCreateUserId) {
            button.isClickable = true
            button.setOnClickListener {
                RetrofitBuilder.networkService.deleteRoom(deleteRoomId).enqueue(object :
                    Callback<DefaultResponseData> {
                    override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DefaultResponseData>,
                        response: Response<DefaultResponseData>
                    ) {
                        val res = response.body()
                        Log.e("res",res.toString())
                        if(res?.message == null) {
                            Toast.makeText(button.context, "성공적으로 삭제되었습니다", Toast.LENGTH_SHORT).show()
                            button.findNavController().navigate(R.id.action_navigation_current_project_to_navigation_matching_list)
                        }
                    }
                })
            }
        } else {
            button.isEnabled = false
            button.isClickable = false
        }
    }

    @BindingAdapter("leaveRoomId" , "leaveCreateUserId")
    @JvmStatic
    fun leave(button: Button, leaveRoomId : Int, leaveCreateUserId: Int) {

        Log.e("id","${SingleTon.prefs.userId} $leaveCreateUserId")
        if (SingleTon.prefs.userId != leaveCreateUserId) {
            button.setOnClickListener {
                RetrofitBuilder.networkService.leaveRoom(leaveRoomId, SingleTon.prefs.userId)
                    .enqueue(object : Callback<DefaultResponseData> {
                        override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<DefaultResponseData>,
                            response: Response<DefaultResponseData>
                        ) {
                            val res = response.body()
                            Log.e("res", res.toString())
                            if(res?.message == null) {
                                Toast.makeText(button.context, "방에서 나왔습니다.", Toast.LENGTH_SHORT).show()
                                button.findNavController().navigate(R.id.action_navigation_current_project_to_navigation_matching_list)
                            }
                        }
                    })
            }
        } else {
            button.isEnabled = false
            button.isClickable = false
        }
    }

    @BindingAdapter("startRoomId","roomStatus")
    @JvmStatic
    fun start(button: Button, startRoomId : Int, roomStatus : String?) {
        when(roomStatus) {
            "OPEN" -> {
                button.setOnClickListener {
                    RetrofitBuilder.networkService.startRoom(startRoomId)
                        .enqueue(object : Callback<DefaultResponseData> {
                            override fun onFailure(call: Call<DefaultResponseData>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<DefaultResponseData>,
                                response: Response<DefaultResponseData>
                            ) {
                                val res = response.body()
                                Log.e("res", res.toString())
                                if (res?.message == null) {
                                    Toast.makeText(
                                        button.context,
                                        "프로젝트를 시작합니다",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    button.findNavController()
                                        .navigate(R.id.action_navigation_current_project_to_navigation_matching_list)
                                }
                            }
                        })
                }
            }
            "START" -> button.isEnabled = false
            "FULL" -> button.isEnabled = false
        }
    }
}