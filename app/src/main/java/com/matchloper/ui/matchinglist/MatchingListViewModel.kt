package com.matchloper.ui.matchinglist

import android.util.Log
import android.widget.RadioButton
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.R
import com.matchloper.data.*
import com.matchloper.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingListViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder

    val projectState = ObservableArrayList<ProjectStateData>()
    val currentRoomId = MutableLiveData<Int>()
    val userInfoData = ObservableArrayList<UserInfo>()
    val currentProjectInfo = MutableLiveData<RoomInfoData>()

    val currentBackEndPosition = MutableLiveData<Int>()
    val currentFrontEndPosition = MutableLiveData<Int>()
    val currentAndroidPosition = MutableLiveData<Int>()
    val currentIosPosition = MutableLiveData<Int>()

    val wholeBackEndPosition = MutableLiveData<Int>()
    val wholeFrontEndPosition = MutableLiveData<Int>()
    val wholeAndroidPosition = MutableLiveData<Int>()
    val wholeIosPosition = MutableLiveData<Int>()


    fun getRoomList() {
        projectState.clear()

        retrofitBuilder.networkService.getRooms().enqueue(object : Callback<FindRoomResponseData> {
            override fun onFailure(call: Call<FindRoomResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<FindRoomResponseData>,
                response: Response<FindRoomResponseData>
            ) {
                val res = response.body()
                Log.e("res",res.toString())

                val data = res?.data!!
                data.forEachIndexed { index, roomInfoData ->
                    when(roomInfoData.status) {
                        "FULL" -> {
                            projectState.add(ProjectStateData(roomInfoData.name,roomInfoData.status,roomInfoData.roomId))
                        }
                        "OPEN" -> {
                            projectState.add(ProjectStateData(roomInfoData.name,"매칭중",roomInfoData.roomId))
                        }
                        "CLOSED" -> {
                            projectState.add(ProjectStateData(roomInfoData.name,"매칭완료",roomInfoData.roomId))
                        }
                        "START" -> {
                            projectState.add(ProjectStateData(roomInfoData.name,roomInfoData.status,roomInfoData.roomId))
                        }
                    }
                }
            }
        })
    }

    fun getOpenedRoom() {
        projectState.clear()

        retrofitBuilder.networkService.getOpenedRoom().enqueue(object : Callback<FindRoomResponseData> {
            override fun onFailure(call: Call<FindRoomResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<FindRoomResponseData>,
                response: Response<FindRoomResponseData>
            ) {
                val res = response.body()
                Log.e("res",res.toString())

                val data = res?.data!!
                data.forEachIndexed { index, roomInfoData ->
                    projectState.add(ProjectStateData(roomInfoData.name,"매칭중",roomInfoData.roomId))
                }
            }
        })
    }

    fun getCurrentProjectInfo() {
        userInfoData.clear()

        currentBackEndPosition.value = 0
        currentFrontEndPosition.value = 0
        currentAndroidPosition.value = 0
        currentIosPosition.value = 0

        wholeBackEndPosition.value = 0
        wholeFrontEndPosition.value = 0
        wholeAndroidPosition.value = 0
        wholeIosPosition.value = 0

        retrofitBuilder.networkService.getRoom(currentRoomId.value!!.toInt()).enqueue(object : Callback<FindRoomOneResponseData> {
            override fun onFailure(call: Call<FindRoomOneResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<FindRoomOneResponseData>,
                response: Response<FindRoomOneResponseData>
            ) {

                val res = response.body()
                currentProjectInfo.value = res?.data

                res?.data?.roomPositions?.forEach { roomPosition ->
                    when(roomPosition.position) {
                        "BACKEND" -> wholeBackEndPosition.value = roomPosition.count
                        "FRONTEND" -> wholeFrontEndPosition.value = roomPosition.count
                        "ANDROID" -> wholeAndroidPosition.value = roomPosition.count
                        "IOS" -> wholeIosPosition.value = roomPosition.count
                    }
                }

                res?.data?.users?.forEach { userInfo ->
                    userInfoData.add(userInfo)

                    when(userInfo.userPositions[0].type) {
                        "BACKEND" -> currentBackEndPosition.value = currentBackEndPosition.value!! + 1
                        "FRONTEND" -> currentFrontEndPosition.value = currentFrontEndPosition.value!! + 1
                        "ANDROID" -> currentAndroidPosition.value = currentAndroidPosition.value!! + 1
                        "IOS" -> currentIosPosition.value = currentIosPosition.value!! + 1
                    }
                }
            }
        })
    }

    fun onRadioButtonClicked(radioButton: RadioButton) {
        val checked = radioButton.isChecked

        when(radioButton.id) {
            R.id.wholeRoom -> {
                if(checked) getRoomList()
            }

            R.id.openedRoom -> {
                if(checked) getOpenedRoom()
            }
        }
    }
}