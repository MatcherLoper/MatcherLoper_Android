package com.matchloper.ui.matchinglist

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.data.FindRoomResponseData
import com.matchloper.data.ProjectStateData
import com.matchloper.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingListViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder

    val projectState = ObservableArrayList<ProjectStateData>()

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
                repeat(data.size) {
                    projectState.add(ProjectStateData(data[it].name,data[it].status))
                }
            }
        })
    }
}