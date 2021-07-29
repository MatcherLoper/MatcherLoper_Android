package com.matchloper.util

import android.content.Intent
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matchloper.adapter.PositionInfoRecyclerViewAdapter
import com.matchloper.data.PositionInfoData
import com.matchloper.view.MatchOpenDialogActivity

object DataBindingUtils {

    @BindingAdapter("matchingOpen")
    @JvmStatic
    fun matchingOpen(button : Button, id : Int) {
        button.setOnClickListener {
            val intent = Intent(button.context,MatchOpenDialogActivity::class.java)
            button.context.startActivity(intent)
        }
    }

    @BindingAdapter("itemList")
    @JvmStatic
    fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<PositionInfoData>){
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = PositionInfoRecyclerViewAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as PositionInfoRecyclerViewAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

//    @BindingAdapter("spinnerAdapter")
//    @JvmStatic
//    fun spinnerAdapter(spinner: Spinner) {
//        ArrayAdapter.createFromResource(spinner.context, R.array.position_array,
//        android.R.layout.simple_spinner_item).also {
//            adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }
//    }
}