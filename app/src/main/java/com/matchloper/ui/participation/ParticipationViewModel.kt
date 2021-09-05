package com.matchloper.ui.participation

import android.view.View
import android.widget.AdapterView
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.SingleTon
import com.matchloper.data.EntryViewData
import com.matchloper.data.EntryViewResponseData
import com.matchloper.data.RoomPosition
import com.matchloper.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParticipationViewModel : ViewModel() {

    private val retrofitBuilder = RetrofitBuilder

    val id = MutableLiveData<Int>().apply {
        this.value = SingleTon.prefs.userId
    }

    val teamProjectName = MutableLiveData<String>()
    val positionData = ObservableArrayList<RoomPosition>()
    val position = MutableLiveData<String>()
    val count = MutableLiveData<String>()
    val area = MutableLiveData<String>()

    val entryData = MutableLiveData<EntryViewData>()

    fun addPositionInfo() {
        if(position.value != null && position.value != "" && count.value != "" && count.value!!.toInt() > 0) {

            if(positionData.find { it.position == position.value }?.count != null) {

                positionData.add(RoomPosition(positionData.find { it.position == position.value }!!.count + count.value!!.toInt(),
                    position.value.toString()))

                positionData.remove(positionData.find { it.position == position.value })
            }
            else {
                positionData.add(RoomPosition(count.value!!.toInt(),position.value.toString()))
            }

            position.value = ""
            count.value = ""
        }
    }

    fun getEntry() {
        retrofitBuilder.networkService.getEntryView().enqueue(object : Callback<EntryViewResponseData>{
            override fun onFailure(call: Call<EntryViewResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<EntryViewResponseData>,
                response: Response<EntryViewResponseData>
            ) {
                val res = response.body()!!
                if(res.message == null) entryData.value = res.data

            }
        })
    }

    val clickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            if(p0?.getItemAtPosition(p2).toString() != "포지션 선택") position.value = when (p0?.getItemAtPosition(p2).toString()) {
                "백엔드" -> "BACKEND"
                "프론트엔드" -> "FRONTEND"
                "안드로이드" -> "ANDROID"
                "iOS" -> "IOS"
                else -> ""
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

    val areaSpinnerClick = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            area.value = p0?.getItemAtPosition(p2).toString()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }

}