package com.matchloper.ui.participation

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import androidx.databinding.ObservableArrayList
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
        if(position.value != null && position.value != "" && count.value != "" && count.value!!.toInt() > 0) {

            if(positionData.find { it.position == position.value }?.count != null) {

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

    val clickListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            if(p0?.getItemAtPosition(p2).toString() != "포지션을 선택하세요") position.value = p0?.getItemAtPosition(p2).toString()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

}