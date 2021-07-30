package com.matchloper.ui.participation

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matchloper.data.PositionInfoData

class HomeViewModel : ViewModel() {

    val id = MutableLiveData<Int>().apply {
        this.value = 1
    }

    val teamProjectName = MutableLiveData<String>()
    val positionData = ObservableArrayList<PositionInfoData>()
    val position = MutableLiveData<String>()
    val count = MutableLiveData<String>()

    fun addPositionInfo() {

        if(position.value != "" && count.value != "" && count.value!!.toInt() > 0) {

            if(positionData.find {
                it.position == position.value
            }?.count != null) {

                positionData.add(PositionInfoData(position.value.toString(),
                    positionData.find { it.position == position.value }!!.count + count.value!!.toInt()))

                positionData.remove(positionData.find {
                    it.position == position.value
                })
            }
            else {
                positionData.add(PositionInfoData(position.value.toString(), count.value!!.toInt()))
            }


            position.value = ""
            count.value = ""
        }
    }

}